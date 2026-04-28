package pointingApplication;

import java.io.*;
import java.time.LocalTime;

public class Pointing implements Serializable {
    //Attributes
    private int idStaff;
    private LocalTime startHour;
    private Float endHour;
    private String date;

    //Constructors


    //Methods
    public static void Ex1() throws Exception{
        //Serialisation
        try(FileOutputStream fos = new FileOutputStream("donnees.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            //oos.writeObject();
        }
        //DeSerialisation

        Pointing les_donnees = null;
        try(FileInputStream file = new FileInputStream("donnees.ser");
            ObjectInputStream in = new ObjectInputStream(file);
        ) {
            les_donnees = (Pointing) in.readObject();
            System.out.println(les_donnees.date);
        }
    }
}
