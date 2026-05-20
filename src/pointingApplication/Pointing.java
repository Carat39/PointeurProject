package pointingApplication;

import Time.TimeManagement;
import mainApplication.management.Staff;
import mainApplication.management.StaffList;

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
    private LocalTime hour;
    private LocalDate date;

    //Constructors

    public Pointing(){
        uuidStaff = UUID.randomUUID();
        hour = LocalTime.MIN;
        date = LocalDate.MIN;
    }

    //Creation du premier pointage d'un membre du Staff. C'est lui que Romain appellera
    public Pointing(UUID L_idStaff) throws Exception{
        uuidStaff = L_idStaff;
        hour = TimeManagement.getHourNow();
        date = TimeManagement.getDateNow();
    }

    public Pointing(UUID L_idStaff, LocalTime shour, LocalTime ehour, LocalDate d) throws Exception{
        uuidStaff = L_idStaff;
        setStartHour(shour);
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

    public static Pointing deserialisation(String filename) throws Exception{
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
        hour = newSHour;
    }

    public void setDate(LocalDate newDate){ date= newDate; }

    public LocalTime getHour(){ return hour; }

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
        System.out.println(les_donnees.hour);
        System.out.println(les_donnees.date);
    }

    //Sera dans l'API de Romain
    public static void Jepointe(String The_Name, String The_Surname) throws Exception{
        Staff theStaff = StaffList.find(The_Name,The_Surname);
        //Est-ce que le staff existe? Forcément mais renvoie la référence
        if (theStaff != null){
            //Est-ce qu-il a pointé 2 fois aujourd'hui
            if (!theStaff.existence2PointingToday()) {
                //Il a déjà pointé 2 fois
            }
            else{
                Pointing thePointing = new Pointing(theStaff.getuuidStaff());
                //On sérialise a ce moment la
                thePointing.serialisation("pointing.ser");
                Pointing Pointingdeserialise = new Pointing();
                Pointingdeserialise = Pointing.deserialisation("pointing.ser");
                theStaff.addPointing("pointing.ser");
                theStaff.affichepointage();
            }
        }
    }
}

