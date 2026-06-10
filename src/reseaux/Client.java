package reseaux;

import mainApplication.management.StaffList;

import java.io.*;
import java.net.*;

/**
 * Client allows a TCP/IP request with a server.
 * Client may send pointing,pointingList or StaffList
 * @author Les Scribes de Broadway
 *
 */
public class Client {
    /**
     * Connection, data sent and takeback of them
     * @param ip Server Ip
     * @param port Server Port
     * @param point The object we sent (pointing)
     */
    public static void connection(String ip, int port, Object point){
        try{
            Socket my_socket = new Socket();
            my_socket.connect(new InetSocketAddress(ip, port), 5000);
            try{
                ObjectOutputStream output = new ObjectOutputStream(my_socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(my_socket.getInputStream());
                output.writeObject(point);
                //On récupère la liste actualisée et on la sérialise
                StaffList theStaffList = (StaffList) input.readObject();
                theStaffList.serialisationStaffList("staffList.ser");
                disconnection(my_socket,output,input);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Disconnection close traffics lines
     * @param mysocket The Socket
     * @param output output stream of the socket  Where we send data
     * @param input input stream of the socket Where we receive data
     */
    public static void disconnection(Socket mysocket, ObjectOutputStream output, ObjectInputStream input){
        try{
            //close stream
            output.close();
            input.close();
            //close socket
            mysocket.close();
        } catch (Exception e) {
            System.out.println();
        }
    }

    /**
     * Connexion/Actualisation of data (staffList,pointingList)
     * @param ip The Ip destination
     * @param port The port of destination
     * @param pointingList the object we send (a pointingList here)
     */
    public static boolean testerConnexion(String ip, int port,Object pointingList) {
        try{
            try{
                Socket mySocket = new Socket();
                mySocket.connect(new InetSocketAddress(ip, port), 5000);
                ObjectOutputStream output = new ObjectOutputStream(mySocket.getOutputStream()); //La où j'envoie au serveur
                ObjectInputStream input = new ObjectInputStream(mySocket.getInputStream()); // La où on reçoit du serveur

                output.writeObject(pointingList);
                //Serialisation of actual staffList
                StaffList theStaffList = (StaffList) input.readObject();
                theStaffList.serialisationStaffList("staffList.ser");
                disconnection(mySocket,output,input);
                return true;
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
