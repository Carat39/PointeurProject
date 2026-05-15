package mainApplication.management;

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
    }

    public UUID getuiidStaff(){return uuidStaff;}
    public String getname(){return name;}
    public String getsurname(){return surname;}
    public int getextratime(){return extratime;}
    public Departement getdepartement(){return departement;}
    public WorkSchedule getworkschedule(){return workschedule;}

    public void setuiidStaff(UUID The_UUID){uuidStaff = The_UUID;}
    public void setname(String The_Name){name = The_Name;}
    public void setsurname(String The_Surname){surname = The_Surname;}
    public void setextratime(int The_ExtraTime){extratime = The_ExtraTime;}
    public void setdepartement(Departement The_Departement){departement = The_Departement;}
    public void setworkschedule(WorkSchedule The_WorkSchedule){workschedule = The_WorkSchedule;}

    public void calcExtraTime(){
        PointingList the_list = null;
        Pointing the_pointing = the_pointing = the_list.getElemList(uuidStaff, TimeManagement.getDate());
        TimeManagement.getDate().getDayOfWeek();

    }
}
