package views;

import utils.Variables;

import javax.swing.*;
import java.awt.*;

/**
 * VIEW – Page d'accueil (page 1).
 * Construit son propre panneau et expose ses composants interactifs
 * via des getters. Aucune logique métier, aucun listener.
 */
public class Page1 {

    private final JPanel panel;
    private final JButton closeBtn;
    private final JButton checkBtn;
    private final JButton clockedBtn;

    public Page1() {
        panel = new JPanel(null);
        panel.setBackground(Variables.PASTEL_PEACH);

        closeBtn   = Variables.createCloseBtn();
        checkBtn   = Variables.createNavBtn("Saved Work time",
                Variables.HEIGHT - 7 * (Variables.HEIGHT / 10));
        clockedBtn = Variables.createNavBtn("Clocked in Now",
                Variables.HEIGHT - 11 * (Variables.HEIGHT / 20));

        panel.add(Variables.createTitle("Time Tracker v1.1"));
        panel.add(closeBtn);
        panel.add(checkBtn);
        panel.add(clockedBtn);
    }

    public JPanel  getPanel()      { return panel; }
    public JButton getCloseBtn()   { return closeBtn; }
    public JButton getCheckBtn()   { return checkBtn; }
    public JButton getClockedBtn() { return clockedBtn; }
}
