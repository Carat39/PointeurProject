package views;

import views.utils.Variables;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * VIEW – Assembleur de l'interface.
 *
 * Les pages 1/2/3 sont leurs propres classes,
 * cet assembleur nous permet de les lier toutes
 * ensembles et de faciliter le travail du controller.
 */
public class Assembleur_pages {

    private final JFrame frame;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    //Ce sont ici des sous-vues pour le controller
    private Page1 page1;
    private Page2 page2;
    private Page3 page3;

    public Assembleur_pages() {
        frame = new JFrame("Pointeuse Manager");
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
    }

    /**
     * Assemble les pages.
     *
     * @param allEmployees liste de tous les employés
     * @param workingEmployees liste des employés qui ont pointé 1 fois aujourd'hui
     * @param years liste des années disponibles
     */
    public void build(ArrayList<String> allEmployees, ArrayList<String> workingEmployees, ArrayList<String> years) {

        frame.setSize(Variables.WIDTH, Variables.HEIGHT);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        page1 = new Page1();
        page2 = new Page2(allEmployees, years);      // tous les employés
        page3 = new Page3(workingEmployees);         // seulement ceux qui bossent

        cardPanel.add(page1.getPanel(), "page1");
        cardPanel.add(page2.getPanel(), "page2");
        cardPanel.add(page3.getPanel(), "page3");

        frame.setContentPane(cardPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //getters
    public JFrame getFrame(){return frame;}
    public CardLayout getCardLayout(){return cardLayout;}
    public JPanel getCardPanel(){return cardPanel;}


    //getters pour le controller
    public Page1 getPage1() { return page1; }

    public Page2 getPage2() { return page2; }

    public Page3 getPage3() { return page3; }
}