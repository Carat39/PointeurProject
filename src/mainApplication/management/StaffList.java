package mainApplication.management;

import pointingApplication.Pointing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;

/**
 * This class manages and eventually stores all employees of the company.
 * Employees are stored in an ArrayList from which individuals can be
 * added, deleted, or searched for via the methods of this class.
 * This class is also serializable-friendly
 * (see methods associated with serialization and deserialization).
 *
 * @author Les Scribes de Broadway
 */
public class StaffList implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private ArrayList<Staff> theStafflist = new ArrayList<Staff>();

    //Constructors
    public StaffList() {
    }

    /**
     * This constructor add all the Staff in the parameter's array in the staff list.
     *
     * @param theStaff The array to copy in the staff list attribute.
     */
    public StaffList(Staff[] theStaff) {
        for (Staff staff: theStaff){
            theStafflist.add(staff);
        }
    }

    /**
     * Add a unique staff member to the staff list attribute.
     *
     * @param Le_Staff The staff to add.
     */
    public void add(Staff Le_Staff) {
        theStafflist.add(Le_Staff);
    }

    /**
     * This method does the same thing as the constructor above,
     * except it verify if each staff already exist in the attribute.
     *
     * @see StaffList#StaffList(Staff[])
     *
     * @param thePointingList The array to copy in the staff list attribute.
     */
    public void add(PointingList thePointingList) throws Exception {
        for (Pointing thePointing: thePointingList.getthelist()){
            Staff theStaff = find(thePointing.getUuidStaff());
            theStaff.addPointing(thePointing);
        }
    }

    /**
     * Remove a Staff (find by its ID) only if existing (in the list).
     *
     * @param L_UUID The ID of the Staff to remove.
     */
    public void remove(UUID L_UUID) {
        theStafflist.remove(find(L_UUID));
    }

    /**
     *
     *
     * @return the size of the staff list attribute.
     */
    public int getsize() {
        return theStafflist.size();
    }

    /**
     * Search for the existence of a Staff in the List.
     *
     * @param L_UUID The ID of the Staff we want to verify.
     * @return The_Staff if he exists.
     */
    public Staff find(UUID L_UUID) {
        boolean find = false;
        Staff The_Staff = null;
        int index = 0;
        while (!find) {
            The_Staff = theStafflist.get(index);
            if (The_Staff.getuuidStaff().equals(L_UUID)) {
                find = true;
            }
            index++;
        }
        return The_Staff;
    }

    /**
     * Does the same thing as the above find method, but it is searching by name & surname.
     *
     * @see StaffList#find(UUID)
     *
     * @param The_Name Name of the Staff.
     * @param The_Surname Surname of the Staff.
     * @return The_Staff if he exists.
     */
    public Staff find(String The_Name, String The_Surname) {
        boolean find = false;
        Staff The_Staff = null;
        int index = 0;
        while (!find) {
            The_Staff = theStafflist.get(index);
            if (The_Staff.getname().equals(The_Name) && The_Staff.getsurname().equals(The_Surname)) {
                find = true;
            }
            index++;
        }
        return The_Staff;
    }

    /**
     * Serialize the StaffList into a file.
     *
     * @param filename Name of the file to create.
     * @throws Exception Triggered if the file cannot be created in the desired directory structure.
     */
    public void serialisationStaffList(String filename) throws Exception {
        String classPath = System.getProperty("java.class.path");
        String arborescence = "src/";
        if (classPath.equals(".")){
            arborescence = "../";
        }
        try (FileOutputStream fos = new FileOutputStream(arborescence+filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(this);
        }
    }

    /**
     * Deserialize the StaffList from a file.
     *
     * @param filename Name of the file to take data from.
     * @return the data.
     * @throws Exception Triggered if the file is not found in the given directory tree.
     */
    public static StaffList deserialisationStaffList(String filename) throws Exception {
        String classPath = System.getProperty("java.class.path");
        String arborescence = "src/";
        if (classPath.equals(".")){
            arborescence = "../";
        }
        StaffList les_donnees = null;
        try (FileInputStream file = new FileInputStream(arborescence+filename);
             ObjectInputStream in = new ObjectInputStream(file)
        ) {
            les_donnees = (StaffList) in.readObject();
        }
        return les_donnees;
    }

    /**
     * Print all the Staff in the List.
     */
    public void showAllWorkers() {
        System.out.println(" ");
        for (Staff worker : theStafflist) {
            System.out.println(worker.getname() + " " + worker.getsurname());
        }
        System.out.println(" ");
    }

    /*
     * Fonction pour ARTHUR!!!
     * Renvoie la liste des Employés qui ont pointé cond et cond seulement fois
     * */

    /**
     * Select (and return) all the Staff that have pointed a certain amount of time
     * (to choose by cond parameter).
     *
     * @param cond Number of time the staff should have been pointing.
     * @return The Staff List verified.
     */
    public StaffList saveworkersfilter(int cond) {
        StaffList workingworker = new StaffList();
        for (Staff worker : theStafflist) {
            //A t-il pointé <cond> et <cond> seulement fois?
            if (worker.condPointingADay(cond, 0)) {
                // Comme je sais pas exactement ce que tu veux soit tu garde le retour de la StaffList,
                // soit tu modifie la ligne suivante pour l'ajouter dans ta liste déroulante
                workingworker.add(worker);
            }
        }
        return workingworker;
    }

    /**
     * @return The Staff List.
     */
    public ArrayList<Staff> getStaffList() {
        return theStafflist;
    }

    /**
     * Method that test creation of Staff & StaffList.
     */
    public static void testStaffList() {
        StaffList ma_List = new StaffList();
        Staff staff1 = new Staff();
        Staff staff2 = new Staff();
        staff1.setname("JenMichel");
        staff1.setsurname("Port");
        staff1.setextratime(15);
        staff2.setname("Matudis");
        ma_List.add(staff1);
        System.out.println(ma_List.find("JenMichel", "Port").getextratime());

    }

    /**
     * Convert The Staff list into a String list composed by the name & surname of each Staff member in the list.
     *
     * @return The String list.
     */
    public ArrayList<String> getStafflist() {
        ArrayList<String> res = new ArrayList<>();
        for (Staff s : theStafflist) {
            res.add(s.getname() + " " + s.getsurname());
        }
        return res;
    }

    /**
     * @return A list of years that are available for pointing (from 2016 to 2026).
     */
    public ArrayList<String> getAvailableYears() {
        ArrayList<String> years = new ArrayList<>();
        for (int y = 2026; y >= 2016; y--) {
            years.add(String.valueOf(y));
        }
        return years;
    }
}