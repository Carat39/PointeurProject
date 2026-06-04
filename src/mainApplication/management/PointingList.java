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
    private static final long serialVersionUID = 123456789L;

    //Attributes
    private ArrayList<Pointing> thelist = new ArrayList<Pointing>();


    //Constructors

    //Methods
    /**
     * Retourne la valeur de la liste de pointage.
     *
     * @return theList.
     */
    public ArrayList<Pointing> getthelist(){return thelist;}

    /**
     * Retourne les deux pointages effectués dans la journée passée en paramètre
     * sous forme d'une ArrayList de 2 élements (pointage d'arrivée en première position
     * et pointage de départ en deuxième position).
     *
     * @param The_date La date à laquelle on souhaite récupérer les pointages.
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
     * Vérifie si l'employé a pointé autant de fois qu'indiqué par le paramètre cond à la date donnée.
     * Retourne vrai si le nombre de pointage(s) correspond à la condition, et faux sinon.
     *
     * @param The_date La date à laquelle on souhaite vérifier les pointages.
     * @param cond Le nombre de fois où l'employé aurait dû pointer pour que la méthode renvoie true.
     *
     * @return true ou false.
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
     * Ajoute un pointage (de type pointing) à la fin de la liste de pointages.
     *
     * @param pointing La date à laquelle on souhaite vérifier les pointages.
     */
    public void addElem(Pointing pointing){
        // Tester exception on sait jamais
        thelist.add(pointing);
    }

    public void addElem(String filename) throws Exception{
        Pointing pointing = new Pointing();
        pointing = deserialisationm(filename);
        thelist.add(pointing);
    }

    /**
     * Sérialise les données des pointages stockés dans la liste sous forme d'un fichier de flux au format ser.
     *
     * @param filename Nom exact du fichier à générer (se créera dans le dossier src/ du projet).
     *
     * @exception Exception Déclenchée si le fichier ne peut être créé à l'arborescence souhaitée.
     */
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

    /**
     * Désérialise les données des pointages à partir d'un flux.
     * Cela implique d'avoir réalisé une sérialisation au préalable et de donner le nom
     * du fichier généré par cette action correctement (utilisation du chemin relatif).
     *
     * @param filename Nom du fichier à désérialiser.
     *
     * @exception Exception Déclenchée si le fichier n'est pas trouvable à l'arborescence donnée.
     */
    public Pointing deserialisationm(String filename) throws Exception{
        Pointing les_donnees = null;
        try(FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file)
        ) {
            les_donnees = (Pointing) in.readObject();
        }
        return les_donnees;
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

    /**
     * Méthode de test sur l'instanciation de la classe PointingList.
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

}
