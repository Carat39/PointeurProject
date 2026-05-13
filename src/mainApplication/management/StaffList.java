package mainApplication.management;

import pointingApplication.Pointing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StaffList {
    private static ArrayList<Staff> theStafflist = new ArrayList<Staff>();

    //Constructors
    public StaffList(){

    }

    public void add(Staff Le_Staff){
        theStafflist.add(Le_Staff);
    }
    public void remove(UUID L_UUID){
        theStafflist.remove(L_UUID);
    }



    public static void testStaffList(){
        StaffList ma_List = new StaffList();
        Staff staff1 = new Staff();
        Staff staff2 = new Staff();
        staff1.setname("JenMichel");
        staff2.setname("Mathudi");
        ma_List.add(staff1);

        //System.out.println(ma_List.get(staff1.getuiidStaff()).getname());

    }
}
