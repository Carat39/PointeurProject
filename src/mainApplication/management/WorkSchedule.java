package mainApplication.management;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * This class allows you to manage an employee's schedule over a week.
 * It is assumed that each employee has a fixed and theoretical arrival and departure time at the company.
 * Furthermore, no breaks are included in this schedule.
 * It is theoretically possible for an employee to work 7 days a week for 20 hours each day.
 * However, no employee can have a workday spread over several days (for example, starting one day in the evening and finishing the next morning).
 *
 * @author Les Scribes de Broadway
 */
public class WorkSchedule implements Serializable {
    //Attributes
    private static final long serialVersionUID = 123456789L;
    private LocalTime[] schedule;

    //Constructors
    /**
     * This constructor produces an empty schedule.
     */
    public WorkSchedule(){
        schedule= new LocalTime[14];
    }

    /**
     * This constructor produces a timetable with the schedule table passed as a parameter.
     *
     * @param timetable The timetable chart to be transformed into a schedule.
     *
     * @exception Exception Triggered if the provided table has more than 14 data points (it is impossible to have more than 7 days in a week).
     */
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
    /**
     * Modifies the schedule created by the builder using the table passed as a parameter.
     * (Same functionality as the comfort builder).
     *
     * @see WorkSchedule#WorkSchedule(LocalTime[])
     *
     * @param timetable The timetable chart to be transformed into a schedule.
     */
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

    /**
     * Returns the employee's schedule.
     *
     * @return schedule.
     */
    public LocalTime[] getSchedule(){ return schedule; }

    /**
     * Converts the DayOfWeek type day to an integer from 0 to 6
     * (0 for MONDAY, 6 for SUNDAY).
     *
     * @param the_day The day to be converted.
     */
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