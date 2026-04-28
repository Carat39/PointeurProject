package pointingApplication;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class TestSerialisation implements Serializable{
    public static void Ex1() throws Exception{
        int heure = java.time.LocalTime.now().getHour();
        System.out.println(heure);

        //Serialisation
        try(FileOutputStream fos = new FileOutputStream("thedate.dat");
            DataOutputStream oos = new DataOutputStream(fos);
        ){
            oos.writeInt(heure);
        }
        //DeSerialisation
        try(FileInputStream file = new FileInputStream("thedate.dat");
            DataInputStream in = new DataInputStream(file);
        ) {
            int nvheure = in.readInt() +12;
            System.out.println(nvheure);
        }
    }

    public static void SerialDateComplete() throws Exception{
        int heure = java.time.LocalTime.now().getHour();
        System.out.println(heure);

        //Serialisation
        try(FileOutputStream fos = new FileOutputStream("thedate.dat");
            DataOutputStream oos = new DataOutputStream(fos);
        ){
            oos.writeInt(heure);
        }
    }
}
