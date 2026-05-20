package controller;

import views.Assembleur_pages;
import utils.temp_serialisation;


/**
 * CONTROLLER – Point d'entrée de l'application.
 * Instancie le Model, injecte les données, construit la View,
 * puis connecte tous les "listeners" de navigation.
 */
public class IHM_Manager {

    private final temp_serialisation model;
    private final Assembleur_pages view;

    public IHM_Manager() {
        model = new temp_serialisation();
        view  = new Assembleur_pages();

        loadData();   // <-- remplacer par la désérialisation réelle
        view.build(model.getEmployees(), model.getAvailableYears());
        bindActions();
    }

    /**
     * Chargement des données (provisoire – à remplacer par la désérialisation).
     * Les setters du Model sont déjà prêts pour recevoir les listes.
     */
    private void loadData() {
        java.util.ArrayList<String> employees = new java.util.ArrayList<String>();
        employees.add("Nino Maigret");
        employees.add("Romain Blaquart");
        employees.add("Clément Sauger");
        employees.add("Carl Esswein");
        employees.add("Arthur Brajot");
        model.setEmployees(employees);

        java.util.ArrayList<String> years = new java.util.ArrayList<>();
        for (int y = 2026; y >= 2016; y--) years.add(String.valueOf(y));
        model.setAvailableYears(years);
    }

    /**
     * Attache tous les listeners de navigation et de fermeture.
     * Le Controller dialogue avec chaque Page via les getters de views.View_pages,
     * puis via les getters de chaque Page.
     */
    private void bindActions() {
        // Fermeture
        view.getPage1().getCloseBtn().addActionListener(e -> view.getFrame().dispose());
        view.getPage2().getCloseBtn().addActionListener(e -> view.getFrame().dispose());
        view.getPage3().getCloseBtn().addActionListener(e -> view.getFrame().dispose());

        // Navigation page 1 → 2 / 3
        view.getPage1().getCheckBtn() .addActionListener(e -> view.getCardLayout().show(view.getCardPanel(), "page2"));
        view.getPage1().getClockedBtn().addActionListener(e -> view.getCardLayout().show(view.getCardPanel(), "page3"));

        // Retour pages 2 et 3 → 1
        view.getPage2().getBackBtn().addActionListener(e -> view.getCardLayout().show(view.getCardPanel(), "page1"));
        view.getPage3().getBackBtn().addActionListener(e -> view.getCardLayout().show(view.getCardPanel(), "page1"));
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(IHM_Manager::new);
    }
}
