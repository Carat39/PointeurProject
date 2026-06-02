package reseaux;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servertestnepasprendreencompte {
    public static void main(String[] args){
        try{
            int port = 1234;
            if (args.length != 0){
                String sport = args[0];
                port = Integer.parseInt(sport);
            }
            ServerSocket my_server = new ServerSocket(port);
            System.out.println("Lancement du serveur sur le port " + port);

            Socket my_socket = my_server.accept();
            DataInputStream stream = new DataInputStream(my_socket.getInputStream());

            String a = stream.readUTF();
            System.out.println(a);
            stream.close();
            my_server.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
