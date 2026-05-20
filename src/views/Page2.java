package views;

import utils.Variables;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * VIEW – Page « Saved work time » (page 2).
 * Construit son propre panneau et expose ses composants interactifs
 * via des getters. Aucune logique métier, aucun listener.
 */
public class Page2 {

    private final JPanel panel;
    private final JButton closeBtn;
    private final JButton backBtn;
    private final JComboBox<String> employeeList;
    private final JComboBox<String> yearList;

    public Page2(ArrayList<String> employees, ArrayList<String> years) {
        panel = new JPanel(null);
        panel.setBackground(Variables.PASTEL_YELLOW);

        closeBtn     = Variables.createCloseBtn();
        backBtn      = Variables.createBackBtn();
        employeeList = Variables.createEmployeeList(employees);
        yearList     = Variables.createYearList(years);

        panel.add(Variables.createTitle("Saved work time"));
        panel.add(closeBtn);
        panel.add(backBtn);
        panel.add(employeeList);
        panel.add(yearList);
    }

    public JPanel              getPanel()        { return panel; }
    public JButton             getCloseBtn()     { return closeBtn; }
    public JButton             getBackBtn()      { return backBtn; }
    public JComboBox<String>   getEmployeeList() { return employeeList; }
    public JComboBox<String>   getYearList()     { return yearList; }
}
