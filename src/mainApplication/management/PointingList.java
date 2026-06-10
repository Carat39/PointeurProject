package mainApplication.management;

import pointingApplication.Pointing;

import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.UUID;

/**
 * This class manages and eventually stores all employee time entries.
 * It stores this information in an ArrayList attribute and allows you to retrieve, append,
 * or serialize the data using its methods.
 *
 * @author Les Scribes de Broadway
 */
public class PointingList implements Serializable {
    private static final long serialVersionUID = 123456789L;

    //Attributes
    private ArrayList<Pointing> thelist = new ArrayList<Pointing>();


    //Constructors

    //Methods
    /**
     * Returns the value of the score list.
     *
     * @return theList.
     */
    public ArrayList<Pointing> getthelist(){return thelist;}

    public ArrayList<Pointing> getElemList(LocalDate The_date){
        Pointing a_pointing = null;
        ArrayList<Pointing> the_pointings = new ArrayList<>();
        boolean find = false;
        int indice = 0;
        while(indice < thelist.size() && !find){
            a_pointing = thelist.get(indice);
            if ((a_pointing.getDate()).equals(The_date)){
                find = true;
                the_pointings.add(a_pointing);
                the_pointings.add(thelist.get(indice+1));
            }
            else{
                indice ++;
            }
        }
        return the_pointings;
    }

    public Pointing getElemList(UUID uui, LocalDate The_date){
        Pointing the_pointing = null;
    /**
     * Returns the two time entries made during the previous day as a parameter
     * as an ArrayList of 2 elements (arrival time entry in first position
     * and departure time entry in second position).
     *
     * @param The_date The date on which we wish to retrieve the scores.
     *
     * @return the_pointings.
     */
    public ArrayList<Pointing> getElemList(LocalDate The_date){
        Pointing a_pointing = null;
        ArrayList<Pointing> the_pointings = new ArrayList<>();
        boolean find = false;
        int indice = 0;
        while(indice < thelist.size() && !find){
            a_pointing = thelist.get(indice);
            if ((a_pointing.getDate()).equals(The_date)){
                find = true;
                the_pointings.add(a_pointing);
                the_pointings.add(thelist.get(indice+1));
            }
            else{
                indice ++;
            }
        }
        return the_pointings;
    }

    /**
     * Checks if the employee has clocked in as many times as specified by the `cond` parameter on the given date.
     * Returns true if the number of clock-ins matches the condition, and false otherwise.
     *
     * @param The_date The date on which we want to check the scores.
     * @param cond The number of times the employee would have had to clock in for the method to return true.
     *
     * @return true or false.
     */
    //A-t-il pointé cond times à la date donnée ?
    public boolean pointingCondTimes(LocalDate The_date, int cond){
        Pointing the_pointing = null;
        LocalTime Hourprec = LocalTime.MIN;
        boolean condPointing = true;
        int comptage = 0;
        int indice = 0;
        while(indice < thelist.size() && condPointing){
            the_pointing = thelist.get(indice);
            if (the_pointing.getHour().equals(Hourprec)){
                condPointing = false;
            }
            if (the_pointing.getDate().equals(The_date)){
                Hourprec = the_pointing.getHour();
                comptage++;
                // Il y a eu 2 pointages ou 1 pointage alors qu'on voulait observer qu'il n'y avait pas de pointage justement
                if (comptage == 2 || cond == 0){
                    condPointing = false;
                }
            }
            indice ++;
        }
        return comptage == cond;
    }

    /**
     * Adds a pointer (of type pointing) to the end of the list of points.
     *
     * @param pointing The date on which we want to check the scores.
     */
    public boolean uniquePointing(Pointing the_pointing){
        Pointing pointingInList = null;
        boolean unique = true;
        int cond = 0;
        int indice = thelist.size()-1;
        if (indice > 100){
            //La liste est trop grande
            cond = 100;
        }
        while(indice >= 0 && unique){
            pointingInList = thelist.get(indice);
            if ((the_pointing.getUuidStaff().equals(pointingInList.getUuidStaff()))
                    && (the_pointing.getHour().equals(pointingInList.getHour()))
                    && (the_pointing.getDate().equals(pointingInList.getDate()))){
                unique = false;
            }
            indice --;
        }
        return unique;
    }

    public void addElem(Pointing pointing){
        // Tester exception on sait jamais
        if (uniquePointing(pointing)){
            thelist.add(pointing);
        }
        else{
            System.out.println("This pointing already exists.");
        }
    }

    public void addElem(String filename) throws Exception{
        Pointing pointing = new Pointing();
        pointing = deserialisationm(filename);
        thelist.add(pointing);
    }

    /**
     * Serializes the data from the score data stored in the list as a stream file in ser format.
     *
     * @param filename Exact name of the file to generate (will be created in the src/ folder of the project).
     *
     * @exception Exception Triggered if the file cannot be created in the desired directory structure.
     */
    public void serialisation(String filename) throws Exception{
        String classPath = System.getProperty("java.class.path");
        String arborescence = "src/";
        if (classPath.equals(".")){
            arborescence = "../";
        }
        try(FileOutputStream fos = new FileOutputStream(arborescence+filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos) // peut être ici le my_socket.getOutputStream()
        ){
            oos.writeObject(this);
        }
    }

    /**
     * Deserializes timekeeping data from a stream.
     * This requires prior serialization and correctly naming the generated file (using a relative path).
     *
     * @param filename Name of the file to deserialize.
     *
     * @exception Exception Triggered if the file is not found in the given directory tree.
     */
    public Pointing deserialisationm(String filename) throws Exception{
        String classPath = System.getProperty("java.class.path");
        String arborescence = "src/";
        if (classPath.equals(".")){
            arborescence = "../";
        }
        Pointing les_donnees = null;
        try(FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file)
        ) {
            les_donnees = (Pointing) in.readObject();
        }
        return les_donnees;
    }

    /**
     * Deserializes timekeeping data from a stream.
     * This requires prior serialization and correctly naming the generated file (using a relative path).
     *
     * @param filename Name of the file to deserialize.
     *
     * @exception Exception Triggered if the file is not found in the given directory tree.
     */
    //déserialise à partir d'un flux de données
    public static PointingList deserialisation(String filename) throws Exception{
        String classPath = System.getProperty("java.class.path");
        String arborescence = "src/";
        if (classPath.equals(".")){
            arborescence = "../";
        }
        PointingList les_donnees = null;
        try(FileInputStream file = new FileInputStream(arborescence+filename);
            ObjectInputStream in = new ObjectInputStream(file)
        ) {
            les_donnees = (PointingList) in.readObject();
        }
        return les_donnees;
    }

    /**
     * Test method on the instantiation of the PointingList class.
     */
    public void testArrayPointing(){
        PointingList TheList = new PointingList();
        for(int indice = 0; indice < 10; indice++){
            Pointing Thepoint1 = new Pointing();
            TheList.addElem(Thepoint1);
        }
        UUID stock = thelist.getFirst().getUuidStaff(); ;
        /*Pointing lemploye = TheList.getElemList(stock, LocalDate.now());
        System.out.println(lemploye.getUuidStaff());
        System.out.println(lemploye.getStartHour());
        System.out.println(lemploye.getEndHour());
        System.out.println(lemploye.getDate());
        */
    }
    public void removeLast() {
        if (!thelist.isEmpty()) {
            thelist.remove(thelist.size() - 1);
        }
    }

}
