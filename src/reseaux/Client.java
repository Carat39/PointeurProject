package reseaux;

import mainApplication.management.StaffList;
import pointingApplication.Pointing;

import java.io.*;
import java.net.*;


public class Client {
    // 10.231.44.109 //Ip de ma 4G
    // 1234 //Un port lambda

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
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            my_socket.close();

        } catch (Exception e) {
            System.out.println();
        }
    }

    public static void disconnection(Socket my_socket, DataOutputStream stream){
        try{
            //fermeture stream
            stream.close();
            //fermeture socket
            my_socket.close();
        } catch (Exception e) {
            System.out.println();
        }
    }

}
