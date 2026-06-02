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
 * Gestion d'erreur prise en compte.
 */
public class IHM_Manager {

    private final temp_serialisation model;
    private final Assembleur_pages view;
    private java.util.ArrayList<String> allEmployees;
    private java.util.ArrayList<String> workingEmployees;
    private StaffList allStaff; //pour les recherches sur les données des 'listeners'

    public IHM_Manager() {
        model = new temp_serialisation();
        view  = new Assembleur_pages();

        loadData();
        view.build(allEmployees, workingEmployees, model.getAvailableYears());
        bindActions();
    }

    /**
     * Chargement des données depuis la désérialisation.
     * Page 2 : tous les employés.
     * Page 3 : employés actuellement au travail c'est à dire ceux qui ont pointé 1 fois aujourd'hui.
     */
    private void loadData() {
        try {
            allStaff = StaffList.deserialisationStaffList("donnees.ser");
            StaffList workingNow = allStaff.saveworkersfilter(1);

            //allEmployees     = allStaff.getStafflist();
            //workingEmployees = workingNow.getStafflist();

        } catch (Exception e) {
            e.printStackTrace();
            allStaff = new StaffList();

            allEmployees = new ArrayList<>();
            allEmployees.add("Erreur chargement employés");

            workingEmployees = new ArrayList<>();
            workingEmployees.add("Erreur chargement employés");
        }

        ArrayList<String> years = new ArrayList<>();
        for (int y = 2026; y >= 2016; y--) {
            years.add(String.valueOf(y));
        }
        model.setAvailableYears(years);
    }

    /**
     * Nettoyage des "listeners"
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
    }

    /**
     * On recupere ici l'employé et l'année puis
     * on regarde ses pointages avant d'envoyer à l'affichage
     */
    private void refreshShifts() {
        String selectedEmployee = (String) view.getPage2().getEmployeeList().getSelectedItem();
        String selectedYear = (String) view.getPage2().getYearList().getSelectedItem();

        if (selectedEmployee == null || selectedYear == null) {
            return;
        }
        if (selectedEmployee.equals("Erreur chargement employés")) {
            return;
        }

        String[] parts = selectedEmployee.split(" ", 2);

        if (parts.length < 2) {
            return;
        }

        String name = parts[0];
        String surname = parts[1];

        Staff staff;
        try {
            staff = allStaff.find(name, surname);
        } catch (Exception e) {
            view.getPage2().updateShiftDisplay("Employé introuvable.");
            return;
        }

        int year = Integer.parseInt(selectedYear);
        PointingList pl = staff.getPointingList();
        ArrayList<Pointing> pointings = pl.getthelist();

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-14s %-12s %-12s%n", "Date", "Arrivée", "Départ"));
        sb.append("-".repeat(40)).append("\n");

        int i = 0;
        int shiftsCount = 0;

        while (i < pointings.size()) {
            Pointing p1 = pointings.get(i);

            if (p1.getDate().getYear() != year) {
                i++;
                //continue permet de continuer après ce if, on ne s'arrete pas ici
                continue;
            }

            if (i + 1 < pointings.size() && pointings.get(i + 1).getDate().equals(p1.getDate())) {
                Pointing p2 = pointings.get(i + 1);
                sb.append(String.format("%-14s %-12s %-12s%n",p1.getDate().toString(),p1.getHour().toString(),p2.getHour().toString()));
                shiftsCount++;
                i += 2;

            } else {
                //pointage seul, la personne est actuellement au travail.
                sb.append(String.format("%-14s %-12s %-12s%n",p1.getDate().toString(), p1.getHour().toString(), "(manquant)"));
                shiftsCount++;
                i++;
            }
        }

        if (shiftsCount == 0) {
            sb = new StringBuilder("Aucun shift trouvé pour " + selectedEmployee + " en " + selectedYear + ".");
        } else {
            sb.append("-".repeat(40)).append("\n");
            sb.append("Total heures supplémentaires cumulées : ").append(staff.getextratime()).append(" min");
        }

        view.getPage2().updateShiftDisplay(sb.toString());
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(IHM_Manager::new);
    }
}