package mainApplication.management;

import java.util.ArrayList;
import java.util.UUID;

public class StaffList {
    private ArrayList<Staff> theStafflist = new ArrayList<Staff>();

    //Constructors
    public StaffList(){}

    public StaffList(ArrayList<Staff> otherStaffList){
        for (Staff s : otherStaffList){
            theStafflist.add(s);
        }
    }

    public void add(Staff Le_Staff){
        theStafflist.add(Le_Staff);
    }

    public void remove(UUID L_UUID){
        theStafflist.remove(find(L_UUID));
    }

    public int getsize() {return theStafflist.size();}

    public Staff find(UUID L_UUID){
        boolean find = false;
        Staff The_Staff = null;
        int index = 0;
        while (!find){
            The_Staff = theStafflist.get(index);
            if (The_Staff.getuuidStaff().equals(L_UUID)) {
                find = true;
            }
            index ++;
        }
        return The_Staff;
    }

    /*
    * I'll be necesary find so no need to worry about the null
    * */
    public  Staff find(String The_Name, String The_Surname){
        boolean find = false;
        Staff The_Staff = null;
        int index = 0;
        while (!find){
            The_Staff = theStafflist.get(index);
            if (The_Staff.getname().equals(The_Name) && The_Staff.getsurname().equals(The_Surname)) {
                find = true;
            }
            index ++;
        }
        return The_Staff;
    }

    public void showAllWorkers(){
        System.out.println(" ");
        for (Staff worker : theStafflist){
            System.out.println(worker.getname() + " " + worker.getsurname());
        }
        System.out.println(" ");
    }

    /*
    * Fonction pour ARTHUR!!!
    * Renvoie la liste des Employés qui ont pointé cond et cond seulement fois
    * */
    public StaffList saveworkersfilter(int cond){
        StaffList workingworker = new StaffList();
        for (Staff worker : theStafflist){
            //A t-il pointé <cond> et <cond> seulement fois?
            if (worker.condPointingADay(cond,0)){
                // Comme je sais pas exactement ce que tu veux soit tu garde le retour de la StaffList,
                // soit tu modifie la ligne suivante pour l'ajouter dans ta liste déroulante
                workingworker.add(worker);
            }
        }
        return workingworker;
    }

    public static void testStaffList(){
        StaffList ma_List = new StaffList();
        Staff staff1 = new Staff();
        Staff staff2 = new Staff();
        staff1.setname("JenMichel");
        staff1.setsurname("Port");
        staff1.setextratime(15);
        staff2.setname("Matudis");
        ma_List.add(staff1);
        System.out.println(ma_List.find("JenMichel","Port").getextratime());

    }
}
