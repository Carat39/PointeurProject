package mainApplication.management;

import Time.TimeManagement;
import pointingApplication.Pointing;
import java.io.Serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.UUID;

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
    public Staff(){
        uuidStaff = UUID.randomUUID();
        name = "";
        surname = "";
        extratime = 0;
        departement = Departement.WORKERS;
    }

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

    public boolean condPointingADay(int cond, long lessDay){
        return pointingList.pointingCondTimes(uuidStaff,TimeManagement.getDateNow().minusDays(lessDay),cond);
    }

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
    public void addPointing(Pointing thePointing) throws Exception{
        pointingList.addElem(thePointing);
    }

    public void addPointing(String filename) throws Exception{
        pointingList.addElem(filename);
    }


    public int getnbpointage(){
        return pointingList.getthelist().size();
    }


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

    public void calcExtraTime(){
        PointingList the_list = null;
        TimeManagement theDate= new TimeManagement();
        //Retrieves the user's pointing as of today's date.
        Pointing the_pointing = the_list.getElemList(uuidStaff, theDate.getDate());
        //Gets today's date in an integer number (from 0 to 6 starting from Monday).
        int the_day= WorkSchedule.convertWeekDay(theDate.getDate().getDayOfWeek());

        //Calculate the difference between the arrival/departure time and the time scheduled on the timetable.
        int startDif= workschedule.getSchedule()[the_day*2].compareTo(the_pointing.getHour());
        //int endDif= workschedule.getSchedule()[the_day*2+1].compareTo(the_pointing.getEndHour());
        //Adding/Deleting overtime hours if working hours have not been respected
        extratime += startDif;
        //extratime += endDif;
    }
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