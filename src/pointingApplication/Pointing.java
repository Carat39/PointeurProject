package pointingApplication;

import java.io.*;
import java.time.LocalTime;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


public class Pointing implements Serializable{
    //Attributes
    private UUID uuidStaff;
    private LocalTime startHour;
    private LocalTime endHour;
    private LocalDate date;

    //Constructors

    public Pointing(){
        uuidStaff = UUID.randomUUID();
        startHour = LocalTime.MIN;
        endHour = LocalTime.MIN;
        date = LocalDate.MIN;
    }

    public Pointing(UUID L_idStaff){
        uuidStaff = L_idStaff;
        setStartHour();
        setEndHour();
        setDate();
    }

    //Methods
    public void serialisation(String filename) throws Exception{
        try(FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(this);
        }
    }

    public Pointing deserialisation(String filename) throws Exception{
        Pointing les_donnees = null;
        try(FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
        ) {
            les_donnees = (Pointing) in.readObject();
        }
        return les_donnees;
    }

    public UUID getUuidStaff() {
        return uuidStaff;
    }

    public void setStartHour(){
        startHour = round(LocalTime.now());
    }

    public void setEndHour(){
        endHour= round(LocalTime.now());
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

    public static void serialisationtest() throws Exception{
        Pointing p = new Pointing();

        //Serialisation
        p.serialisation("donnees.ser");
        //DeSerialisation

        Pointing les_donnees = new Pointing();
        les_donnees = les_donnees.deserialisation("donnees.ser");
        System.out.println(les_donnees.uuidStaff);
        System.out.println(les_donnees.startHour);
        System.out.println(les_donnees.endHour);
        System.out.println(les_donnees.date);
    }

    public static void testarroundi(){
        Pointing p = new Pointing();
        p.setStartHour();
        LocalTime heuretest= p.getStartHour();
        System.out.println("Il est : " + heuretest);
        heuretest= p.round(heuretest);
        System.out.println("Arrondis à la quinzaine : " + heuretest);
    }
}
