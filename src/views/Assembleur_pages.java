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
    private Page4 page4;
    private Page5 page5;
    private Page6 page6;

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
        page4 = new Page4(allEmployees);
        page5 = new Page5();
        page6 = new Page6();

        cardPanel.add(page1.getPanel(), "page1");
        cardPanel.add(page2.getPanel(), "page2");
        cardPanel.add(page3.getPanel(), "page3");
        cardPanel.add(page4.getPanel(), "page4");
        cardPanel.add(page5.getPanel(), "page5");
        cardPanel.add(page6.getPanel(), "page6");

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

    public Page4 getPage4() { return page4; }

    public Page5 getPage5() { return page5; }

    public Page6 getPage6() { return page6; }
}