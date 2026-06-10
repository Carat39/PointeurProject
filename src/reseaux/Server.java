package reseaux;

import mainApplication.management.PointingList;
import mainApplication.management.Staff;
import mainApplication.management.StaffList;
import pointingApplication.Pointing;

import java.io.*;
import java.net.*;

/**
 * Server allows the TCP/IP connection between a client and the server
 * Furthermore, depending on the object it receives, he will do different
 * manipulations to save and sendback data.
 *
 * @author Les Scribes de Broadway
 *
 */
public class Server {
    /**
     * Main start the server, which can detect connections, as a result unserialisations and serialisations
     * @param args Arguments (not used).
     */
    public static void main(String[] args){
        int port = 1234;
        if (args.length != 0){
            String sport = args[0];
            port = Integer.parseInt(sport);
        }
        try{
            ServerSocket myServer = new ServerSocket(port);
            while (true){
                try{
                    System.out.println("Start server in port " + port);
                    Socket mySocket = myServer.accept();

                    ObjectOutputStream output = new ObjectOutputStream(mySocket.getOutputStream()); //La où on reçois du client
                    ObjectInputStream input = new ObjectInputStream(mySocket.getInputStream()); //La où on reçois du client
                    //On lit ce que l'on a reçu pour observer son type d'objet
                    Object les_donnees = input.readObject();

                    if (les_donnees instanceof Pointing thePointing){
                        System.out.println("It's pointing's API which send a pointing");
                        //We're taking the StaffList
                        StaffList theStaffList = StaffList.deserialisationStaffList("staffList.ser");
                        Staff theStaff = theStaffList.find(thePointing.getUuidStaff());
                        //add pointing
                        theStaff.addPointing(thePointing);
                        //data Actualisation
                        sendback(mySocket,theStaffList,output);
                    }
                    else if (les_donnees instanceof PointingList thePointingList) {
                        System.out.println("It's pointing's API which send pointingList when Connect/Refresh button is pressed");
                        StaffList theStaffList = StaffList.deserialisationStaffList("staffList.ser");
                        //add all pointing to staff members
                        theStaffList.add(thePointingList);
                        //Actualisation des données
                        sendback(mySocket,theStaffList,output);
                    }
                    else if (les_donnees instanceof StaffList theStaffList) {
                        System.out.println("It's main's API which send the new StaffList after a modification of a Staff member or a pointing");
                        theStaffList.serialisationStaffList("staffList.ser");
                        //We have nothing to sendback
                    }
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * sendback serialise theStaffList and send it in the current socket.
     * @param mySocket Les arguments (ne sont pas utilisés).
     * @param theStaffList A StaffList we want to send to client.
     * @param output The output Stream of the socket .
     */
    public static void sendback(Socket mySocket, StaffList theStaffList, ObjectOutputStream output) throws Exception{
        //Actualisation staffList coté serveur
        theStaffList.serialisationStaffList("staffList.ser");
        //On renvoie la list de Staff actualisée au client
        output.writeObject(theStaffList);
    }
}
