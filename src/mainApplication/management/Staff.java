package mainApplication.management;

import Time.TimeManagement;
import pointingApplication.Pointing;
import java.io.Serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.Duration;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.UUID;

/**
 * This class allows you to manage a member of the company's staff.
 * Each member is identified by an ID, a first name, and a last name.
 * Moreover, each employee works in a specific department, has a specific schedule,
 * and a timesheet recorded within the company,
 * resulting in a positive or negative number of overtime hours (in minutes) owed to the company.
 *
 * @author Les Scribes de Broadway
 */
public class Staff implements Serializable {
    private static final long serialVersionUID = 123456789L;
    //Attributes
    private UUID uuidStaff;
    private String name;
    private String surname;
    private int extratime; //en minutes
    private Departement departement;
    private WorkSchedule workschedule;
    private PointingList pointingList;

    //Constructors

    /**
     * This constructor create an empty staff (random values).
     */
    public Staff(){
        uuidStaff = UUID.randomUUID();
        name = "";
        surname = "";
        extratime = 0;
        departement = Departement.WORKERS;
    }

    /**
     * This constructor let you create a specific staff member (Id still created randomly).
     *
     * @param the_Name of the staff member.
     * @param the_Surname of the staff member.
     * @param the_Departement of the staff member.
     * @param the_WorkSchedule of the staff member.
     */
    public Staff(String the_Name, String the_Surname,
                 Departement the_Departement, WorkSchedule the_WorkSchedule){
        uuidStaff = UUID.randomUUID();
        name = the_Name;
        surname = the_Surname;
        extratime = 0;
        departement = the_Departement;
        workschedule = the_WorkSchedule;
        pointingList = new PointingList();
    }

    //Setters
    public UUID getuuidStaff(){return uuidStaff;}
    public String getname(){return name;}
    public String getsurname(){return surname;}
    public int getextratime(){return extratime;}
    public Departement getdepartement(){return departement;}
    public WorkSchedule getworkschedule(){return workschedule;}
    public PointingList getPointingList(){return pointingList;}

    //Getters
    public void setuuidStaff(UUID The_UUID){uuidStaff = The_UUID;}
    public void setname(String The_Name){name = The_Name;}
    public void setsurname(String The_Surname){surname = The_Surname;}
    public void setextratime(int The_ExtraTime){extratime = The_ExtraTime;}
    public void setdepartement(Departement The_Departement){departement = The_Departement;}
    public void setworkschedule(WorkSchedule The_WorkSchedule){workschedule = The_WorkSchedule;}
    public void setPointingList(PointingList The_PointingList){pointingList = The_PointingList;}

    /**
     * Verify if the staff a certain amount of pointing on a certain date.
     *
     * @param cond Number of pointing
     * @param lessDay Number of day ago where we want to verify pointing.
     * @return true if verified, false else.
     */
    public boolean condPointingADay(int cond, long lessDay){
        return pointingList.pointingCondTimes(TimeManagement.getDateNow().minusDays(lessDay),cond);
    }

    /**
     * Serializes the data from the score data stored in the list as a stream file in ser format.
     *
     * @param filename Exact name of the file to generate.
     * @throws Exception Triggered if the file cannot be created in the desired directory structure.
     */
    public void serialisation(String filename) throws Exception{
        String classPath = System.getProperty("java.class.path");
        String arborescence = "src/";
        if (classPath.equals(".")){
            arborescence = "../";
        }
        try(FileOutputStream fos = new FileOutputStream(arborescence+filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos)
        ){
            oos.writeObject(this);
        }
    }

    /**
     * Deserializes timekeeping data from a stream.
     * This requires prior serialization and correctly naming the generated file (using a relative path).
     *
     * @param filename Name of the file to deserialize.
     *
     * @exception Exception Triggered if the file is not found in the given directory tree.
     */
    public Staff deserialisation(String filename) throws Exception{
        String classPath = System.getProperty("java.class.path");
        String arborescence = "src/";
        if (classPath.equals(".")) {
            arborescence = "../";
        }
        Staff les_donnees = null;
        try(FileInputStream file = new FileInputStream(arborescence+filename);
            ObjectInputStream in = new ObjectInputStream(file)
        ) {
            les_donnees = (Staff) in.readObject();
        }
        return les_donnees;
    }

    //Ajoute à partir d'un flux de donnée ou lève une exception
    /**
     * Add a pointing to the PointingList attribute.
     *
     * @param thePointing Data stream where to retrieve the data.
     * @throws Exception Triggered if the data stream can't be read or is incorrect.
     */
    public void addPointing(Pointing thePointing) throws Exception{
        pointingList.addElem(thePointing);
        if (condPointingADay(2, DAYS.between(thePointing.getDate(), LocalDate.now()))){
            calcExtraTime();
        }
    }

    /**
     * Does the same thing as the addPointing method above but take the data from a file directly.
     *
     * @see Staff#addPointing(Pointing)
     *
     * @param filename Name of the file to retrieve data from.
     * @throws Exception If the file can't be read.
     */
    public void addPointing(String filename) throws Exception{
        pointingList.addElem(filename);
    }

    /**
     * @return the number of pointing the staff does.
     */
    public int getnbpointage(){
        return pointingList.getthelist().size();
    }


    /**
     * A method to test the pointing with different staff.
     *
     * @throws Exception
     */
    public static void testStaffPointing() throws Exception{
        WorkSchedule workSchedule = null;
        Staff Romain = new Staff("Blaquart","Romain",Departement.WORKERS,workSchedule);
        Staff Arthur = new Staff("Brajot","Arthur",Departement.ADMINISTRATION,workSchedule);
        Staff Clement = new Staff("Sauger","Clement",Departement.COMMUNICATION,workSchedule);
        Staff Nino = new Staff("Maigret","Nino",Departement.MANAGEMENT,workSchedule);

        //Création d'un pointing avec l'heure actuel
        //Avant de créer, il faut vérifier si un pointeur existe déjà à la date donnée
        Pointing pointingRomain = new Pointing(Romain.getuuidStaff(),TimeManagement.getHourNow(),TimeManagement.getDateNow());
    }

    /**
     * Update the overtime that the staff member owed to the company.
     * (Using pointing made by the staff on the curent date).
     */
    public void calcExtraTime(){
        TimeManagement theDate= new TimeManagement();
        //Retrieves the user's pointing as of today's date.
        ArrayList<Pointing> the_pointing = pointingList.getElemList(theDate.getDate());
        //Gets today's date in an integer number (from 0 to 6 starting from Monday).
        int the_day= WorkSchedule.convertWeekDay(theDate.getDate().getDayOfWeek());

        //Calculate the difference between the arrival/departure time and the time scheduled on the timetable.
        LocalTime expectedStart= workschedule.getSchedule()[the_day*2];
        LocalTime expectedEnd= workschedule.getSchedule()[the_day*2+1];
        LocalTime actualStart= (the_pointing.get(0)).getHour();
        LocalTime actualEnd= (the_pointing.get(1)).getHour();

        long startDif= Duration.between(actualStart, expectedStart).toMinutes();
        long endDif= Duration.between(actualEnd, expectedEnd).toMinutes();
        //Adding/Deleting overtime hours if working hours have not been respected
        if (actualStart.isBefore(expectedStart)){
            extratime -= (int)startDif;
        }
        else{
            extratime += (int)startDif;
        }
        if (actualEnd.isBefore(expectedEnd)){
            extratime += (int)endDif;
        }
        else{
            extratime -= (int)endDif;
        }
    }

    /**
     * Keep only the pointing that have been made on a specific year.
     *
     * @param year The year we want the pointing from.
     * @return a new ArrayList with only the pointing from the specific year.
     */
    public ArrayList<Pointing> getPointingsByYear(int year) {
        ArrayList<Pointing> result = new ArrayList<>();
        for (Pointing p : pointingList.getthelist()) {
            if (p.getDate().getYear() == year) {
                result.add(p);
            }
        }
        return result;
    }
}