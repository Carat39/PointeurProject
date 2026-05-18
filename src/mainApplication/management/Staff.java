package mainApplication.management;

import Time.TimeManagement;
import pointingApplication.Pointing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.UUID;

public class Staff {
    //Attributes
    private UUID uiidStaff;
    private String name;
    private String surname;
    private int extratime; //en minutes
    private Departement departement;
    private PointingList pointingList;

    //Constructors
    public Staff(){
        uiidStaff = UUID.randomUUID();
        name = "";
        surname = "";
        extratime = 0;
        departement = Departement.WORKERS;
    }

    public Staff(String the_Name,
                 String the_Surname, Departement the_Departement){
        uiidStaff = UUID.randomUUID();
        name = the_Name;
        surname = the_Surname;
        extratime = 0;
        departement = the_Departement;
    }

    //Seteurs
    public UUID getuiidStaff(){return uiidStaff;}
    public String getname(){return name;}
    public String getsurname(){return surname;}
    public int getextratime(){return extratime;}
    public Departement getdepartement(){return departement;}

    //Getteurs
    public void setuiidStaff(UUID The_UUID){uiidStaff = The_UUID;}
    public void setname(String The_Name){name = The_Name;}
    public void setsurname(String The_Surname){surname = The_Surname;}
    public void setextratime(int The_ExtraTime){extratime = The_ExtraTime;}
    public void setdepartement(Departement The_Departement){departement = The_Departement;}

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

    public static void testStaffPointing(){
        Staff Romain = new Staff("Blaquart","Romain",Departement.WORKERS);
        Staff Arthur = new Staff("Brajot","Arthur",Departement.ADMINISTRATION);
        Staff Clement = new Staff("Sauger","Clement",Departement.COMMUNICATION);
        Staff Nino = new Staff("Maigret","Nino",Departement.MANAGEMENT);
        Pointing pointingRomain = new Pointing(Romain.getuiidStaff(),TimeManagement.getHour(),TimeManagement.getHour(),TimeManagement.getDate());
    }


}
