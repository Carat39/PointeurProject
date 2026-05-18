package mainApplication.management;

import Time.TimeManagement;
import pointingApplication.Pointing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Time.TimeManagement;
import pointingApplication.Pointing;
import java.time.LocalTime;
import java.util.UUID;

public class Staff {
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

    //Seteurs
    public UUID getuuidStaff(){return uuidStaff;}
    public String getname(){return name;}
    public String getsurname(){return surname;}
    public int getextratime(){return extratime;}
    public Departement getdepartement(){return departement;}
    public WorkSchedule getworkschedule(){return workschedule;}
    public PointingList getPointingList(){return pointingList;}

    //Getteurs
    public void setuuidStaff(UUID The_UUID){uuidStaff = The_UUID;}
    public void setname(String The_Name){name = The_Name;}
    public void setsurname(String The_Surname){surname = The_Surname;}
    public void setextratime(int The_ExtraTime){extratime = The_ExtraTime;}
    public void setdepartement(Departement The_Departement){departement = The_Departement;}
    public void setworkschedule(WorkSchedule The_WorkSchedule){workschedule = The_WorkSchedule;}
    public void setPointingList(PointingList The_PointingList){pointingList = The_PointingList;}

    public Pointing existencePointingToday(){
        return pointingList.getElemList(uuidStaff,TimeManagement.getDateNow());
    }

    public void serialisation(String filename) throws Exception{
        try(FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos)
        ){
            oos.writeObject(this);
        }
    }

    public Staff deserialisation(String filename) throws Exception{
        Staff les_donnees = null;
        try(FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file)
        ) {
            les_donnees = (Staff) in.readObject();
        }
        return les_donnees;
    }

    //Ajoute à partir d'un flux de donnée ou lève une exception
    public void addPointing(String filename) throws Exception{
        pointingList.addElem(filename);
    }

    public static void testStaffPointing() throws Exception{
        WorkSchedule workSchedule = null;
        Staff Romain = new Staff("Blaquart","Romain",Departement.WORKERS,workSchedule);
        Staff Arthur = new Staff("Brajot","Arthur",Departement.ADMINISTRATION,workSchedule);
        Staff Clement = new Staff("Sauger","Clement",Departement.COMMUNICATION,workSchedule);
        Staff Nino = new Staff("Maigret","Nino",Departement.MANAGEMENT,workSchedule);

        //Création d'un pointing avec l'heure actuel
        //Avant de créer, il faut vérifier si un pointeur existe déjà à la date donnée
        Pointing pointingRomain = new Pointing(Romain.getuuidStaff(),TimeManagement.getHourNow(),TimeManagement.getHourNow(),TimeManagement.getDateNow());
    }


}
