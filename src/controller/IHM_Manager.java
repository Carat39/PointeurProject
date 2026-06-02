package controller;

import views.Assembleur_pages;
import utils.temp_serialisation;
import mainApplication.management.Staff;
import mainApplication.management.StaffList;
import mainApplication.management.PointingList;
import pointingApplication.Pointing;

import java.util.ArrayList;

/**
 * CONTROLLER
 *
 * Fait a peu près tout ce qui est important,
 * recupere les listeners et affiche ce qu'il faut.
 * Gestion d'erreur prise en compte (normalement hein)
 */

public class IHM_Manager {

    private final temp_serialisation model;
    private final Assembleur_pages view;
    private java.util.ArrayList<String> allEmployees;
    private java.util.ArrayList<String> workingEmployees;
    private StaffList allStaff; //pour les recherches sur les données des 'listeners'


    //constructeuur
    public IHM_Manager() {
        model = new temp_serialisation();
        view  = new Assembleur_pages();

        loadData();
        System.out.println("Working employees: " + allEmployees);
        System.out.println("Working employees: " + workingEmployees);

        view.build(allEmployees, workingEmployees, allStaff.getAvailableYears());
        bindActions();
    }

    /**
     * Chargement des données depuis la désérialisation.
     * Page 2 : tous les employés.
     * Page 3 : employés actuellement au travail c'est à dire ceux qui ont pointé 1 fois aujourd'hui.
     */
    private void loadData() {
        try {
            allStaff = StaffList.deserialisationStaffList("staffList.ser");
            StaffList workingNow = allStaff.saveworkersfilter(1);
            allEmployees = allStaff.getStafflist();
            workingEmployees = workingNow.getStafflist();
        } catch (Exception e) {
            e.printStackTrace();
            allStaff = new StaffList();
            allEmployees = new ArrayList<>();
            allEmployees.add("Erreur chargement employés");
            workingEmployees = new ArrayList<>();
            workingEmployees.add("Erreur chargement employés");
        }
    }

    /**
     * "Branchement" Des listeners sur leurs actions,
     * grossièrement on a ici le lien entre l'action
     * et la méthode, c'est la partie principale du controller
     */
    private void bindActions() {
        // Fermeture
        view.getPage1().getCloseBtn().addActionListener(e -> view.getFrame().dispose());
        view.getPage2().getCloseBtn().addActionListener(e -> view.getFrame().dispose());
        view.getPage3().getCloseBtn().addActionListener(e -> view.getFrame().dispose());

        view.getPage1().getCheckBtn().addActionListener(e -> view.getCardLayout().show(view.getCardPanel(), "page2"));
        view.getPage1().getClockedBtn().addActionListener(e -> view.getCardLayout().show(view.getCardPanel(), "page3"));

        view.getPage2().getBackBtn().addActionListener(e -> view.getCardLayout().show(view.getCardPanel(), "page1"));
        view.getPage3().getBackBtn().addActionListener(e -> view.getCardLayout().show(view.getCardPanel(), "page1"));

        view.getPage2().getEmployeeList().addActionListener(e -> refreshShifts());
        view.getPage2().getYearList().addActionListener(e -> refreshShifts());
        view.getPage3().getEmployeeList().addActionListener(e -> refreshEmployee());
    }

    /**
     * On recupere ici l'employé et l'année puis
     * on regarde ses pointages avant d'envoyer à l'affichage
     */
    private void refreshShifts() {
        String selectedEmployee = (String) view.getPage2().getEmployeeList().getSelectedItem();
        String selectedYear = (String) view.getPage2().getYearList().getSelectedItem();

        if (selectedEmployee == null || selectedYear == null) return;
        if (selectedEmployee.equals("Erreur chargement employés")) return;

        String[] parts = selectedEmployee.split(" ", 2);
        if (parts.length < 2) return;

        Staff staff;
        try {
            staff = allStaff.find(parts[0], parts[1]);
        } catch (Exception e) {
            view.getPage2().updateShiftDisplay("Employé introuvable.");
            return;
        }

        int year = Integer.parseInt(selectedYear);
        view.getPage2().displayPointings(staff.getPointingsByYear(year), year, selectedEmployee, staff.getextratime());
    }

    /**
     * Cette méthode permet d'afficher correctement
     * les heures selon l'employé sélectionné
     */
    private void refreshEmployee() {
        String selectedEmployee = (String) view.getPage3().getEmployeeList().getSelectedItem();

        if (selectedEmployee == null) {return;}
        if (selectedEmployee.equals("Erreur chargement employés")){return;}

        String[] parts = selectedEmployee.split(" ", 2);
        if (parts.length < 2){return;}

        Staff staff;
        try {
            staff = allStaff.find(parts[0], parts[1]);
        } catch (Exception e) {
            return;
        }

        ArrayList<Pointing> pointings = staff.getPointingList().getthelist();
        System.out.println("pointings");
        String arrivalHour = "";
        for (Pointing p : pointings) {
            if (p.getDate().equals(Time.TimeManagement.getDateNow())) {
                arrivalHour = p.getHour().toString();
                break;
            }
        }

        view.getPage3().displayEmployee(staff.getname(), staff.getsurname(), arrivalHour);
    }

    /**
     * Lance le code
     * @param args
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(IHM_Manager::new);
    }
}