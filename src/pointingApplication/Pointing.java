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

    public Pointing(UUID L_idStaff, LocalTime shour, LocalTime ehour, LocalDate d) throws Exception{
        uuidStaff = L_idStaff;
        setStartHour(shour);
        setEndHour(ehour);
        setDate(d);
    }

    //Methods
    public void serialisation(String filename) throws Exception{
        try(FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos)
        ){
            oos.writeObject(this);
        }
    }

    public Pointing deserialisation(String filename) throws Exception{
        Pointing les_donnees = null;
        try(FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file)
        ) {
            les_donnees = (Pointing) in.readObject();
        }
        return les_donnees;
    }

    public UUID getUuidStaff() {
        return uuidStaff;
    }

    public void setIdStaff(UUID newId){ uuidStaff=newId; }

    public void setStartHour(LocalTime newSHour) throws Exception{
        if ((newSHour.toString()).equals("23:45")){
            throw new Exception("Error: cannot point at 23:45 (day schedule terminated)");
        }
        startHour = newSHour;
    }

    public void setEndHour(LocalTime newEhour) throws Exception{
        //handle exception endhour < starthour + starthour and endhour not on the same date
        if (endHour.isBefore(startHour)){
            throw new Exception("Error: Endhour cannot be earlier than Starthour.");
        }
        //Exception dates différentes...
        endHour= newEhour;
    }

    public void setDate(LocalDate newDate){ date= newDate; }

    public LocalTime getStartHour(){ return startHour; }

    public LocalTime getEndHour(){ return endHour; }

    public LocalDate getDate(){ return date; }

    public static void serialisationtest() throws Exception{
        Pointing p = new Pointing();
        p.setIdStaff(UUID.randomUUID());

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
}
