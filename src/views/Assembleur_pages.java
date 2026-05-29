package views;

import views.utils.Variables;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * VIEW – Assembleur de l'interface.
 *
 * Role : faire la fenetre principale et assemble dessus toutes
 * les pages différentes de manière a créer notre IHM.
 *
 * Chaque page est liée à sa propre classe (Page1, Page2, Page3)
 * qui respecte le même schéma : construire de la base et envoie
 * des informations via les getters.
 */
public class Assembleur_pages {

    private final JFrame frame;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    // Sous-vues – le Controller les interroge directement
    private Page1 page1;
    private Page2 page2;
    private Page3 page3;

    public Assembleur_pages() {
        frame      = new JFrame("Pointeuse Manager");
        cardLayout = new CardLayout();
        cardPanel  = new JPanel(cardLayout);
    }

    /**
     * Construit toute l'interface à partir des données fournies par le Controller.
     * À appeler une seule fois après l'instanciation.
     *
     * @param allEmployees     liste de tous les employés → Page2
     * @param workingEmployees liste des employés ayant pointé 1 fois aujourd'hui → Page3
     * @param years            liste des années disponibles → Page2
     */
    public void build(ArrayList<String> allEmployees,
                      ArrayList<String> workingEmployees,
                      ArrayList<String> years) {

        frame.setSize(Variables.WIDTH, Variables.HEIGHT);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        page1 = new Page1();
        page2 = new Page2(allEmployees, years);      // tous les employés
        page3 = new Page3(workingEmployees);          // seulement ceux qui bossent

        cardPanel.add(page1.getPanel(), "page1");
        cardPanel.add(page2.getPanel(), "page2");
        cardPanel.add(page3.getPanel(), "page3");

        frame.setContentPane(cardPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // ---- Getters pour le Controller ----

    public JFrame      getFrame()      { return frame; }
    public CardLayout  getCardLayout() { return cardLayout; }
    public JPanel      getCardPanel()  { return cardPanel; }

    /** Sous-vue page 1 – lien controlleur et page */
    public Page1 getPage1() { return page1; }

    /** Sous-vue page 2 – lien controlleur et page */
    public Page2 getPage2() { return page2; }

    /** Sous-vue page 3 – lien controlleur et page */
    public Page3 getPage3() { return page3; }
}