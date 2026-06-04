package mainApplication.management;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * Cette classe permet de gérer l'emploi du temps d'un employé sur une semaine.
 * Il est considéré que chaque employé a une heure d'arrivée à l'entreprise et de départ
 * fixe et théorique. De plus, aucune pause n'est prévue dans cet emploi du temps.
 * Il est théoriquement possible qu'un employé travail 7j/7 pendant 20h chaque jour.
 * Cependant, aucun employé ne peut avoir une journée de travail étalée sur plusieurs jours
 * (commencer une journée le soir et terminer le lendemain matin par exemple).
 *
 * @author Les Scribes de Broadway
 */
public class WorkSchedule implements Serializable {
    //Attributes
    private static final long serialVersionUID = 123456789L;
    private LocalTime[] schedule;

    //Constructors
    /**
     * Ce constructeur produit un emploi du temps vide.
     */
    public WorkSchedule(){
        schedule= new LocalTime[14];
    }

    /**
     * Ce constructeur produit un emploi du temps avec le tableau d'horaires passé en paramètre.
     *
     * @param timetable Le tableau d'horaires à transformer en emploi du temps.
     *
     * @exception Exception Déclenchée si le tableau fournit possède plus de 14 données (impossibilité d'avoir plus de 7 jours en une semaine).
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
     * Modifie l'emploi du temps créé par le constructeur avec le tableau passé en paramètre.
     * (même fonctionnement que le constructeur de confort).
     *
     * @see WorkSchedule#WorkSchedule(LocalTime[])
     *
     * @param timetable Le tableau d'horaires à transformer en emploi du temps.
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
     * Retourne l'emploi du temps de l'employé.
     *
     * @return schedule.
     */
    public LocalTime[] getSchedule(){ return schedule; }

    /**
     * Convertit le jour de type DayOfWeek en entier de 0 à 6
     * (0 pour MONDAY, 6 pour SUNDAY).
     *
     * @param the_day Le jour à convertir.
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