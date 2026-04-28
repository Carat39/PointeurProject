package pointingApplication;

import java.io.*;
import java.time.LocalTime;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;

public class Pointing {
    //Attributes
    private int idStaff;
    private LocalTime startHour;
    private LocalTime endHour;
    private LocalDate date;

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
    public void serialisation() {

    }

    public void setStartHour(){
        startHour= LocalTime.now();
    }

    public void setEndHour(){
        endHour= LocalTime.now();
    }

        Pointing les_donnees = null;
        try(FileInputStream file = new FileInputStream("donnees.ser");
            ObjectInputStream in = new ObjectInputStream(file);
        ) {
            les_donnees = (Pointing) in.readObject();
            System.out.println(les_donnees.date);
        }
    public void setDate(){
        date= LocalDate.now();
    }

    public LocalTime getStartHour(){
        return startHour;
    }

    public LocalTime getEndHour(){
        return endHour;
    }

    public LocalDate getDate(){
        return date;
    }

    public LocalTime round(LocalTime time){
        int m= time.getMinute();
        LocalTime newTime= time;
        if (m%15!=0){
            if((m%15)<8){
                newTime= time.minusMinutes(m%15);
                newTime= newTime.minusSeconds(newTime.getSecond());
                newTime= newTime.minusNanos(newTime.getNano());
            }
            else{
                newTime= time.plusMinutes(15-m%15);
                newTime= newTime.minusSeconds(newTime.getSecond());
                newTime= newTime.minusNanos(newTime.getNano());
            }
        }
        return newTime;
    }
}
