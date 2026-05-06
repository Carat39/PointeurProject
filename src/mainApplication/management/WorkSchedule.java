package mainApplication.management;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
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
        schedule= new LocalTime[12];
    }

    public WorkSchedule(LocalTime[] timetable){
        schedule= new LocalTime[12];
        if (Arrays.stream(timetable).count()<12){

        }
        else{
            int iterator = 0;
            for (LocalTime lt : schedule){
                schedule[iterator] = timetable[iterator];
            }
        }
    }

    //Methods
    public void setSchedule(LocalTime[] timetable){
        if (Arrays.stream(timetable).count()<12){

        }
        else{
            int iterator = 0;
            for (LocalTime lt : schedule){
                schedule[iterator] = timetable[iterator];
            }
        }
    }

    public LocalTime[] getSchedule(){ return schedule; }
}
