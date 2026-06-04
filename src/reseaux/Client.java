package reseaux;

import mainApplication.management.StaffList;
import pointingApplication.Pointing;

import java.io.*;
import java.net.*;

/**
 * Client permet la connection entre un client et un serveur avec TCP/IP
 *
 * @author Les Scribes de Broadway
 *
 */
public class Client {
    // 10.231.44.109 //Ip de ma 4G
    // 1234 //Un port lambda

    /**
     * Connection et envoie données à un serveur + reception
     * @param ip Ip du serveur
     * @param port Le port d'adressage
     * @param point L'objet qui sera envoyé (un pointeur ici)
     */
    public static void connection(String ip, int port, Object point){
        try{
            Socket my_socket = new Socket(ip,port);
            try{
                ObjectOutputStream output = new ObjectOutputStream(my_socket.getOutputStream()); //La où j'envoie au serveur
                ObjectInputStream input = new ObjectInputStream(my_socket.getInputStream()); // La où on reçoit du serveur
                output.writeObject(point);
                //On récupère la liste actualisée et on la sérialise
                StaffList theStaffList = (StaffList) input.readObject();
                theStaffList.serialisationStaffList("staffList.ser");
                disconnection(my_socket,output,input);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            System.out.println();
        }
    }

    /**
     * Disconnection Ferme les traffics
     * @param my_socket Le socket
     * @param output Là où on envoie les données
     * @param input Là où on reçoit les données
     */
    public static void disconnection(Socket my_socket, ObjectOutputStream output, ObjectInputStream input){
        try{
            //fermeture stream
            output.close();
            input.close();
            //fermeture socket
            my_socket.close();
        } catch (Exception e) {
            System.out.println();
        }
    }

    public static boolean testerConnexion(String ip, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(ip, port), 2000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
