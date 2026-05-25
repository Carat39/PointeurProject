package mainApplication.management;

import java.time.DayOfWeek;
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
            for (LocalTime lt : timetable){
                schedule[iterator] = lt;
                iterator ++;
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
    public static int convertWeekDay(DayOfWeek the_day){
        int inDay;
        switch (the_day){
            case MONDAY:
                inDay= 0;
                break;
            case TUESDAY:
                inDay= 1;
                break;
            case WEDNESDAY:
                inDay= 2;
                break;
            case THURSDAY:
                inDay= 3;
                break;
            case FRIDAY:
                inDay= 4;
                break;
            case SATURDAY:
                inDay= 5;
                break;
            case SUNDAY:
                inDay= 6;
                break;
            default:
                inDay= -1;
                break;
        }
        return inDay;
    }
}