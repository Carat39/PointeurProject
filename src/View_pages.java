import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * VIEW – Assembleur de l'interface Swing.
 *
 * Responsabilité unique : créer la fenêtre, assembler les pages dans
 * le CardLayout et exposer chaque sous-vue au Controller via des getters.
 * Aucune logique métier, aucun listener ici.
 *
 * Chaque page est déléguée à sa propre classe (Page1, Page2, Page3)
 * qui respecte le même contrat : construire son panneau + exposer ses
 * composants via getters.
 */
public class View_pages {

    private final JFrame frame;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    // Sous-vues – le Controller les interroge directement
    private Page1 page1;
    private Page2 page2;
    private Page3 page3;

    public View_pages() {
        frame      = new JFrame("Pointeuse Manager");
        cardLayout = new CardLayout();
        cardPanel  = new JPanel(cardLayout);
    }

    /**
     * Construit toute l'interface à partir des données fournies par le Controller.
     * À appeler une seule fois après l'instanciation.
     */
    public void build(ArrayList<String> employees, ArrayList<String> years) {
        frame.setSize(Variables.WIDTH, Variables.HEIGHT);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        page1 = new Page1();
        page2 = new Page2(employees, years);
        page3 = new Page3(employees);

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

    /** Sous-vue page 1 – le Controller y branche ses listeners. */
    public Page1 getPage1() { return page1; }

    /** Sous-vue page 2 – le Controller y branche ses listeners. */
    public Page2 getPage2() { return page2; }

    /** Sous-vue page 3 – le Controller y branche ses listeners. */
    public Page3 getPage3() { return page3; }
}
