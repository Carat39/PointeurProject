package mainApplication.management;

import java.util.UUID;

public class Staff {
    //Attributes
    private UUID uiidStaff;
    private String name;
    private String surname;
    private int extratime; //en minutes
    private Departement departement;

    //Constructors
    public Staff(){
        uiidStaff = UUID.randomUUID();
        name = "";
        surname = "";
        extratime = 0;
        departement = Departement.WORKERS;
    }

    public Staff(String the_Name,
                 String the_Surname, Departement the_Departement){
        uiidStaff = UUID.randomUUID();
        name = the_Name;
        surname = the_Surname;
        extratime = 0;
        departement = the_Departement;
    }


}
