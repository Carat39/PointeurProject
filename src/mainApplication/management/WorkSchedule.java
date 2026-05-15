package mainApplication.management;

import java.time.LocalTime;
import java.util.Arrays;

public class WorkSchedule {
    //Attributes
    /*
    private LocalTime MondayStart;
    private LocalTime MondayEnd;
    private LocalTime TuesdayStart;
    private LocalTime TuesdayEnd;
    private LocalTime WednesdayStart;
    private LocalTime WednesdayEnd;
    private LocalTime ThursdayStart;
    private LocalTime ThursdayEnd;
    private LocalTime FridayStart;
    private LocalTime FridayEnd;
    private LocalTime SaturdayStart;
    private LocalTime SaturdayEnd;
    private LocalTime SundayStart;
    private LocalTime SundayEnd;
    */
    private LocalTime[] schedule;

    //Constructors
    public WorkSchedule(){
        schedule= new LocalTime[14];
    }

    public WorkSchedule(LocalTime[] timetable) throws Exception{
        schedule= new LocalTime[14];
        if (Arrays.stream(timetable).count()>14){
            throw new Exception("Error: cannot work more than 7 days per week.");
        }
        else{
            int iterator = 0;
            for (LocalTime lt : schedule){
                schedule[iterator] = timetable[iterator];
            }
        }
    }

    //Methods
    public void setSchedule(LocalTime[] timetable) throws Exception{
        if (Arrays.stream(timetable).count()<14){
            throw new Exception("Error: cannot work more than 7 days per week.");
        }
        else{
            int iterator = 0;
            for (LocalTime lt : schedule){
                schedule[iterator] = timetable[iterator];
            }
        }
    }

    public LocalTime[] getSchedule(){ return schedule; }

    //Convert the day of the week from 0 to 6
    public int convertWeekDay(String the_day){
        switch (the_day){
            case "Monday":
                return 0;
            default:
                return -1;
        }
    }
}
