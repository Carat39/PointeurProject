package views;

import views.utils.Variables;

import javax.swing.*;
import java.util.ArrayList;

/**
 * VIEW – Page « Clocked in Now » (page 3).
 * Construit son propre panneau et expose ses composants interactifs
 * via des getters. Aucune logique métier, aucun listener.
 */
public class Page3 {

    private final JPanel panel;
    private final JButton closeBtn;
    private final JButton backBtn;
    private final JComboBox<String> employeeList;

    public Page3(ArrayList<String> employees) {
        panel = new JPanel(null);
        panel.setBackground(Variables.PASTEL_YELLOW);

        closeBtn     = Variables.createCloseBtn();
        backBtn      = Variables.createBackBtn();
        employeeList = Variables.createEmployeeList(employees);

        panel.add(Variables.createTitle("Clocked in Now"));
        panel.add(closeBtn);
        panel.add(backBtn);
        panel.add(employeeList);
    }

    public JPanel            getPanel()        { return panel; }
    public JButton           getCloseBtn()     { return closeBtn; }
    public JButton           getBackBtn()      { return backBtn; }
    public JComboBox<String> getEmployeeList() { return employeeList; }
}
