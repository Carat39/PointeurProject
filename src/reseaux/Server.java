package reseaux;

import mainApplication.management.PointingList;
import mainApplication.management.Staff;
import mainApplication.management.StaffList;
import pointingApplication.Pointing;

import java.io.*;
import java.net.*;

/**
 * Server permet la connection entre un client et un serveur avec TCP/IP
 *
 * @author Les Scribes de Broadway
 *
 */
public class Server {
    /**
     * Main lance le serveur récupère les données si une connection a lieu puis les sérialises
     * @param args Les arguments (ne sont pas utilisés).
     */
    public static void main(String[] args){
        int port = 1234;
        if (args.length != 0){
            String sport = args[0];
            port = Integer.parseInt(sport);
        }
        while (true){
            try{
                ServerSocket my_server = new ServerSocket(port);
                System.out.println("Lancement du serveur sur le port " + port);
                    Socket my_socket = my_server.accept();
                    ObjectOutputStream output = new ObjectOutputStream(my_socket.getOutputStream()); //La où on reçois du client
                    ObjectInputStream input = new ObjectInputStream(my_socket.getInputStream()); //La où on reçois du client
                    System.out.println("local"+my_socket.getLocalAddress());
                    System.out.println("socket"+my_socket.getLocalSocketAddress());
                    System.out.println("3eme"+my_socket.getInetAddress());
                    //Server.serialisationIP(my_socket.getLocalAddress());
                    System.out.println("On lit ce que l'on a reçu pour observer son type d'objet");
                    //On lit ce que l'on a reçu pour observer son type d'objet
                    Object les_donnees = input.readObject();
                    System.out.println("On a lu");


                    if (les_donnees instanceof Pointing thePointing){
                        System.out.println("C'est l'API de la pointeuse qui envoie lors d'un pointage");

                        //C'est l'API de la pointeuse qui envoie lors d'un pointage
                        //On récupère la list de Staff
                        System.out.println(thePointing.getDate());
                        StaffList theStaffList = StaffList.deserialisationStaffList("staffList.ser");
                        Staff theStaff = theStaffList.find(thePointing.getUuidStaff());
                        //On y ajoute le pointeur
                        theStaff.addPointing(thePointing);
                        //Actualisation des données
                        sendback(my_socket,theStaffList,output);
                    }
                    else if (les_donnees instanceof PointingList) {
                        System.out.println("C'est l'API de la pointeuse qui envoie lors de l'ouverture de l'application");

                        //C'est l'API de la pointeuse qui envoie lors de l'ouverture de l'application
                        PointingList thePointingList = (PointingList) input.readObject();
                        StaffList theStaffList = StaffList.deserialisationStaffList("staffList.ser");
                        //ajout de tous les pointages dans pour les membres du Staff
                        theStaffList.add(thePointingList);
                        //Actualisation des données
                        sendback(my_socket,theStaffList,output);
                    }
                    else if (les_donnees instanceof StaffList) {
                        System.out.println("C'est l'API principale qui envoie lors d'une modification d'un Staff ou d'un pointage");

                        //C'est l'API principale qui envoie lors d'une modification d'un Staff ou d'un pointage
                        StaffList theStaffList = (StaffList) input.readObject();
                        //Besoin de ne rien envoyer
                    }
                    my_server.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*
    * Sérialise les données et les renvoient de l'autre côté
    * */
    public static void sendback(Socket my_socket, StaffList theStaffList,ObjectOutputStream output) throws Exception{
        //Actualisation staffList coté serveur
        theStaffList.serialisationStaffList("staffList.ser");
        //On renvoie la list de Staff actualisée au client
        output.writeObject(theStaffList);
    }

    public static void serialisationIP(String filename,String IP) throws Exception{
        String classPath = System.getProperty("java.class.path");
        String arborescence = "src/";
        if (classPath.equals(".")){
            arborescence = "";
        }
        try(FileOutputStream fos = new FileOutputStream(arborescence+filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos) // peut être ici le my_socket.getOutputStream()
        ){
            oos.writeObject(IP);
        }
    }
    public static String deserialisationIP(String filename) throws Exception{
        String classPath = System.getProperty("java.class.path");
        String arborescence = "src/";
        if (classPath.equals(".")){
            arborescence = "";
        }
        String les_donnees = " ";
        try(FileInputStream file = new FileInputStream(arborescence+filename);
            ObjectInputStream in = new ObjectInputStream(file)
        ) {
            les_donnees = (String) in.readObject();
        }
        return les_donnees;
    }
}
