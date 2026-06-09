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

/**
 * Client allows a TCP/IP request with a server.
 * Client may send pointing,pointingList or StaffList
 * @author Les Scribes de Broadway
 *
 */
public class Pointing implements Serializable{
    @Serial
    private static final long serialVersionUID = 123456789L;

    //Attributes
    private UUID uuidStaff;
    private LocalTime hour;
    private LocalDate date;

    //Constructors
    /**
     * Pointing Default Constructor
     * Create a random pointing
     */
    public Pointing(){
        uuidStaff = UUID.randomUUID();
        hour = LocalTime.MIN;
        date = LocalDate.MIN;
    }

    /**
     * Pointing Personnal Constructor
     * @param LidStaff
     * Create a pointing with L_idStaff as uuidStaff
     */
    public Pointing(UUID LidStaff) throws Exception{
        uuidStaff = LidStaff;
        hour = TimeManagement.getHourNow();
        date = TimeManagement.getDateNow();
    }

    /**
     * Pointing Personnal Constructor
     * @param LidStaff UuidStaff
     * @param hour hour of the pointing
     * @param d the date of the pointing
     * Create a pointing with given params.
     */
    public Pointing(UUID LidStaff, LocalTime hour, LocalDate d) throws Exception{
        uuidStaff = LidStaff;
        setHour(hour);
        setDate(d);
    }

    /**
     * serialisation
     * @param filename the output filename
     * serialisation of a pointing into ser file.
     */
    public void serialisation(String filename) throws Exception{
        String classPath = System.getProperty("java.class.path");
        String arborescence = "src/";
        if (classPath.equals(".")){
            arborescence = "../";
        }
        try(FileOutputStream fos = new FileOutputStream(arborescence+filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos) // peut être ici le my_socket.getOutputStream()
        ){
            oos.writeObject(this);
        }
    }

    /**
     * deserialisation
     * @param filename the output filename
     * unserialisation of a pointing from a ser file.
     */
    public static Pointing deserialisation(String filename) throws Exception{
        String classPath = System.getProperty("java.class.path");
        String arborescence = "src/";
        if (classPath.equals(".")){
            arborescence = "../";
        }
        Pointing les_donnees = null;
        try(FileInputStream file = new FileInputStream(arborescence+filename);
            ObjectInputStream in = new ObjectInputStream(file)
        ) {
            les_donnees = (Pointing) in.readObject();
        }
        return les_donnees;
    }

    /**
     * getUuidStaff
     * return uuidStaff
     */
    public UUID getUuidStaff() {
        return uuidStaff;
    }

    /**
     * setUuidStaff
     * @param newId The new uuid
     * set uuidStaff to newId
     */
    public void setIdStaff(UUID newId){ uuidStaff=newId; }

    /**
     * setHour
     * @param newSHour LocalTime the Newhour
     * try to set hour to NewHour
     */
    public void setHour(LocalTime newSHour) throws Exception{
        if ((newSHour.toString()).equals("23:45")){
            throw new Exception("Error: cannot point at 23:45 (day schedule terminated)");
        }
        hour = newSHour;
    }

    /**
     * setHour
     * @param newSHour String the Newhour
     * try to set hour to NewHour with a String this time
     */
    public void setHour(String newSHour) throws Exception{
        setHour(LocalTime.parse(newSHour));
    }

    /**
     * setdate
     * @param newDate the NewDate
     * set hour to newDate
     */
    public void setDate(LocalDate newDate){ date= newDate; }

    /**
     * getHour
     * return hour
     */
    public LocalTime getHour(){ return hour; }

    /**
     * getdate
     * return date
     */
    public LocalDate getDate(){ return date; }

    /**
     * Jepointe
     * @param TheName The name of the employee who is trying to check in/out
     * @param TheSurname The surname of the employee who is trying to check in/out
     * @param theStaffList theStaffList
     * @param thePointingList pointingList
     * @param ip destination ip
     * @param port destination port
     */
    public static void Jepointe(String TheName, String TheSurname, StaffList theStaffList,PointingList thePointingList, String ip, int port) throws Exception{
        Staff theStaff = theStaffList.find(TheName,TheSurname);
        //Find the Staff employee
        if (theStaff != null){
            //Does he have ever pointed 2 times today?
            if (theStaff.condPointingADay(2,0)) {
                throw new Exception("You have already pointed 2 times today!!!");
            }
            else{
                Pointing thePointing = new Pointing(theStaff.getuuidStaff());
               try{
                    //Try sending data
                    Client.connection(ip,port,(Object)thePointing);
               }
               catch (Exception e) {
                   //if it fails, we will make a local serialization
                   System.out.println("Connection failed, saving locally.");
                   try {
                       thePointingList.addElem(thePointing);
                       thePointingList.serialisation("pointingList.ser");
                   } catch (Exception ex) {
                       throw new Exception("Saving error : " + ex.getMessage());
                   }
               }
               //add pointing into staff
               theStaff.addPointing(thePointing);
               //add pointing into local pointingList
               thePointingList.addElem(thePointing);
            }
            //Does he have pointed 2 times yesterday?
            if (!theStaff.condPointingADay(1,1) && theStaff.getnbpointage() >= 3 ) {
                throw new Exception("You haven't pointed 2 times yesterday!!!");
            }
        }
    }
}
