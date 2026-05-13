package mainApplication.management;

import java.util.UUID;

public class Staff {
    //Attributes
    private UUID uiidStaff;
    private String name;
    private String surname;
    private int extratime; //en minutes
    private Departement departement;

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

    public UUID getuiidStaff(){return uiidStaff;}
    public String getname(){return name;}
    public String getsurname(){return surname;}
    public int getextratime(){return extratime;}
    public Departement getepartement(){return departement;}

    public void setuiidStaff(UUID The_UUID){uiidStaff = The_UUID;}
    public void setname(String The_Name){name = The_Name;}
    public void setsurname(String The_Surname){surname = The_Surname;}
    public void setextratime(int The_ExtraTime){extratime = The_ExtraTime;}
    public void setepartement(Departement The_Departement){departement = The_Departement;}


}
