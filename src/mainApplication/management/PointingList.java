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
 * Cette classe permet de gérer et stocker, à terme, tous les pointages réalisés par les employés.
 * Elle stocke ces informations dans un attribut de type ArrayList et permet de récupérer, ajouter,
 * ou sérialiser les données à l'aide de ses méthodes.
 *
 * @author Les Scribes de Broadway
 */
public class PointingList implements Serializable {
    //Attributes
    private static final long serialVersionUID = 123456789L;
    private ArrayList<Pointing> thelist = new ArrayList<Pointing>();

    //Constructors

    //Methods
    /**
     * Retourne la valeur du
     *
     * @param denominator La nouvelle valeur, non nulle, pour le dénominateur.
     */
    public ArrayList<Pointing> getthelist(){return thelist;}

    //Utilité du paramètre sur l'identifiant de staff ??
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
            }
            else{
                indice ++;
            }
        }
        return the_pointings;
    }

    //A-t-il pointé cond times à la date donnée ?
    public boolean pointingCondTimes(UUID l_id, LocalDate The_date, int cond){
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
                // Il y a eu 2 pointage ou 1 pointage alors qu'on voulait observer qu'il n'y avait pas de pointage justement
                if (comptage == 2 || cond == 0){
                    condPointing = false;
                }
            }
            indice ++;
        }
        return comptage == cond;
    }

    public void addElem(Pointing pointing){
        // Tester exception on sait jamais
        thelist.add(pointing);
    }

    public void serialisation(String filename) throws Exception{
        String classPath = System.getProperty("java.class.path");
        String arborescence = "src/";
        if (classPath.equals(".")){
            arborescence = "";
        }
        try(FileOutputStream fos = new FileOutputStream(arborescence+filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos) // peut être ici le my_socket.getOutputStream()
        ){
            oos.writeObject(this);
        }
    }

    //déserialise à partir d'un flux de données
    public static PointingList deserialisation(String filename) throws Exception{
        String classPath = System.getProperty("java.class.path");
        String arborescence = "src/";
        if (classPath.equals(".")){
            arborescence = "";
        }
        PointingList les_donnees = null;
        try(FileInputStream file = new FileInputStream(arborescence+filename);
            ObjectInputStream in = new ObjectInputStream(file)
        ) {
            les_donnees = (PointingList) in.readObject();
        }
        return les_donnees;
    }

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

}
