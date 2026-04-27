package mainApplication.management;

public class Staff {
    //Attributes
    private int idStaff;
    private String name;
    private String surname;
    private int extratime; //en minutes
    private Departement departement;

    //Constructors
    public Staff(){
        idStaff = 0;
        name = "";
        surname = "";
        extratime = 0;
        departement = Departement.WORKERS;
    }

    public Staff(int the_Id, String the_Name,
                 String the_Surname, Departement the_Departement){
        idStaff = the_Id;
        name = the_Name;
        surname = the_Surname;
        extratime = 0;
        departement = the_Departement;
    }


}
