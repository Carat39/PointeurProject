/*package pointingApplication;

import Time.TimeManagement;
import mainApplication.management.Staff;
import mainApplication.management.StaffList;

import java.io.*;
import java.time.LocalTime;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
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

    public Pointing(UUID L_idStaff, LocalTime hour, LocalDate d) throws Exception{
        uuidStaff = L_idStaff;
        setHour(hour);
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

    public void setHour(LocalTime newSHour) throws Exception{
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
    public static void Jepointe(String The_Name, String The_Surname,StaffList theStaffList) throws Exception{
        Staff theStaff = theStaffList.find(The_Name,The_Surname);
        //Est-ce que le staff existe? Forcément mais renvoie la référence
        if (theStaff != null){
            //Est-ce qu-il a pointé 2 fois aujourd'hui
            if (theStaff.condPointingADay(2,0)) {
                //Il a déjà pointé 2 fois
                throw new Exception("You haven allready point 2 times today!!!");
            }
            else{
                Pointing thePointing = new Pointing(theStaff.getuuidStaff());
                //On sérialise a ce moment la
                thePointing.serialisation("pointing.ser");
                Pointing Pointingdeserialise = new Pointing();
                Pointingdeserialise = Pointing.deserialisation("pointing.ser");
                //Ajout du pointage dans la liste du staff
                theStaff.addPointing("pointing.ser");
            }
            //A t-il pointé 2 fois hier (en supposant qu'il a au moins 2 pointages dans son palmarès)
            if (!theStaff.condPointingADay(1,1) && theStaff.getnbpointage() >= 3 ) {
                throw new Exception("You haven't pointed 2 times yesterday!!!");
            }
        }
    }
}
*/
package pointingApplication;

import Time.TimeManagement;
import mainApplication.management.PointingList;
import mainApplication.management.Staff;
import mainApplication.management.StaffList;
import reseaux.Client;

import java.io.*;
import java.time.LocalTime;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.UUID;


public class Pointing implements Serializable{
    private static final long serialVersionUID = 123456789L;

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

    public Pointing(UUID L_idStaff, LocalTime hour, LocalDate d) throws Exception{
        uuidStaff = L_idStaff;
        setHour(hour);
        setDate(d);
    }

    //Methods
    public void serialisation(String filename) throws Exception{
        String classPath = System.getProperty("java.class.path");
        String arborescence = "src/";
        if (classPath.equals(".")){
            arborescence = "";
        }
        try(FileOutputStream fos = new FileOutputStream(arborescence+filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos) // peut être ici le my_socket.getOutputStream()
        ){
            oos.writeObject(this);
        }
    }
    public void serialisation(String filename,DataOutputStream stream) throws Exception{
        try(
                ObjectOutputStream oos = new ObjectOutputStream(stream) // peut être ici le my_socket.getOutputStream()
        ){
            oos.writeObject(this);
        }
    }

    public static Pointing deserialisation(String filename) throws Exception{
        String classPath = System.getProperty("java.class.path");
        String arborescence = "src/";
        if (classPath.equals(".")){
            arborescence = "";
        }
        Pointing les_donnees = null;
        try(FileInputStream file = new FileInputStream(arborescence+filename);
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

    public void setHour(LocalTime newSHour) throws Exception{
        if ((newSHour.toString()).equals("23:45")){
            throw new Exception("Error: cannot point at 23:45 (day schedule terminated)");
        }
        hour = newSHour;
    }

    public void setDate(LocalDate newDate){ date= newDate; }

    public LocalTime getHour(){ return hour; }

    public LocalDate getDate(){ return date; }

    //Sera dans l'API de Romain
    public static void Jepointe(String The_Name, String The_Surname, StaffList theStaffList,PointingList thePointingList, String ip, int port) throws Exception{
        Staff theStaff = theStaffList.find(The_Name,The_Surname);
        System.out.println("NB POINTAGES: " + theStaff.getnbpointage());
        System.out.println("COND TODAY: " + theStaff.condPointingADay(2, 0));
        //Est-ce que le staff existe? Forcément mais renvoie la référence
        if (theStaff != null){
            //Est-ce qu-il a pointé 2 fois aujourd'hui
            if (theStaff.condPointingADay(2,0)) {
                //Il a déjà pointé 2 fois
                throw new Exception("You have already pointed 2 times today!!!");
            }
            else{
                Pointing thePointing = new Pointing(theStaff.getuuidStaff());
                //On sérialise a ce moment la
                try{
                    //"10.231.44.109" //si 4g
                    //192.168.1.19 //si maison maman
                    //192.168.1.18 //si maison papa
                    // 172.25.15.109 //si polytech
                    Client.connection(ip,port,(Object)thePointing);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                //Ajout du pointage dans la liste du staff
                theStaff.addPointing(thePointing);
                //Ajout du pointage dans la liste des pointages de la journée
                thePointingList.addElem(thePointing);
            }
            //A t-il pointé 2 fois hier (en supposant qu'il a au moins 2 pointages dans son palmarès)
            if (!theStaff.condPointingADay(1,1) && theStaff.getnbpointage() >= 3 ) {
                throw new Exception("You haven't pointed 2 times yesterday!!!");
            }
        }
    }
}