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

    public UUID getuuidStaff(){return uuidStaff;}
    public String getname(){return name;}
    public String getsurname(){return surname;}
    public int getextratime(){return extratime;}
    public Departement getdepartement(){return departement;}
    public WorkSchedule getworkschedule(){return workschedule;}

    public void setuuidStaff(UUID The_UUID){uuidStaff = The_UUID;}
    public void setname(String The_Name){name = The_Name;}
    public void setsurname(String The_Surname){surname = The_Surname;}
    public void setextratime(int The_ExtraTime){extratime = The_ExtraTime;}
    public void setdepartement(Departement The_Departement){departement = The_Departement;}
    public void setworkschedule(WorkSchedule The_WorkSchedule){workschedule = The_WorkSchedule;}

    public void calcExtraTime(){
        PointingList the_list = null;
        TimeManagement theDate= new TimeManagement();
        //Retrieves the user's pointing as of today's date.
        Pointing the_pointing = the_list.getElemList(uuidStaff, theDate.getDate());
        //Gets today's date in an integer number (from 0 to 6 starting from Monday).
        int the_day= WorkSchedule.convertWeekDay(theDate.getDate().getDayOfWeek());

        //Calculate the difference between the arrival/departure time and the time scheduled on the timetable.
        int startDif= workschedule.getSchedule()[the_day*2].compareTo(the_pointing.getStartHour());
        int endDif= workschedule.getSchedule()[the_day*2+1].compareTo(the_pointing.getEndHour());
        //Adding/Deleting overtime hours if working hours have not been respected
        extratime += startDif;
        extratime += endDif;
    }
}
