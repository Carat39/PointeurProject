package reseaux;

import mainApplication.management.PointingList;
import mainApplication.management.Staff;
import mainApplication.management.StaffList;
import pointingApplication.Pointing;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args){
        int port = 1234;
        if (args.length != 0){
            String sport = args[0];
            port = Integer.parseInt(sport);
        }
        try{
            ServerSocket my_server = new ServerSocket(port);
            System.out.println("Lancement du serveur sur le port " + port);
            while (true){
                Socket my_socket = my_server.accept();

                ObjectInputStream input = new ObjectInputStream(my_socket.getInputStream()); //La où on reçois du client

                //On lit ce que l'on a reçu pour observer son type d'objet
                Object les_donnees = input.readObject();

                if (les_donnees instanceof Pointing){
                    //C'est l'API de la pointeuse qui envoie lors d'un pointage
                    Pointing thePointing = (Pointing) input.readObject();
                    //On récupère la list de Staff
                    StaffList theStaffList = StaffList.deserialisationStaffList("staffList.ser");
                    Staff theStaff = theStaffList.find(thePointing.getUuidStaff());
                    //On y ajoute le pointeur
                    theStaff.addPointing(thePointing);
                    //Actualisation des données
                    sendback(my_socket,theStaffList);
                }
                else if (les_donnees instanceof PointingList) {
                    //C'est l'API de la pointeuse qui envoie lors de l'ouverture de l'application
                    PointingList thePointingList = (PointingList) input.readObject();
                    StaffList theStaffList = StaffList.deserialisationStaffList("staffList.ser");
                    //ajout de tous les pointages dans pour les membres du Staff;
                    theStaffList.add(thePointingList);
                    //Actualisation des données
                    sendback(my_socket,theStaffList);

                }
                else if (les_donnees instanceof StaffList) {
                    //C'est l'API principale qui envoie lors d'une modification d'un Staff ou d'un pointage
                    StaffList theStaffList = (StaffList) input.readObject();
                    //Besoin de ne rien envoyer
                }


                my_server.close();
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Sérialise les données et les renvoient de l'autre côté
     * */
    public static void sendback(Socket my_socket, StaffList theStaffList) throws Exception{
        ObjectOutputStream output = new ObjectOutputStream(my_socket.getOutputStream()); //La où on envoie au client
        //Actualisation staffList coté serveur
        theStaffList.serialisationStaffList("staffList.ser");
        //On renvoie la list de Staff actualisée au client
        output.writeObject(theStaffList);
    }
}