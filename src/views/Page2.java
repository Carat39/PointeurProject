package views;

import views.utils.Variables;

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
    private final JTextArea shiftArea;

    public Page2(ArrayList<String> employees, ArrayList<String> years) {
        panel = new JPanel(null);
        panel.setBackground(Variables.PASTEL_YELLOW);

        closeBtn     = Variables.createCloseBtn();
        backBtn      = Variables.createBackBtn();
        employeeList = Variables.createEmployeeList(employees);
        yearList     = Variables.createYearList(years);

        // Zone d'affichage des shifts
        shiftArea = new JTextArea();
        shiftArea.setEditable(false);
        shiftArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 13));
        shiftArea.setBackground(Color.WHITE);
        shiftArea.setForeground(Color.DARK_GRAY);
        shiftArea.setText("Sélectionnez un employé et une année.");

        JScrollPane scrollPane = new JScrollPane(shiftArea);
        scrollPane.setBounds(
                Variables.WIDTH / 2 - 300,  Variables.HEIGHT / 2 + 50, 600, 200
        );
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        panel.add(Variables.createTitle("Saved work time"));
        panel.add(closeBtn);
        panel.add(backBtn);
        panel.add(employeeList);
        panel.add(yearList);
        panel.add(scrollPane);
    }

    /** Met à jour la zone de texte avec les shifts formatés. */
    public void updateShiftDisplay(String content) {
        shiftArea.setText(content);
        shiftArea.setCaretPosition(0); // remonte en haut
    }

    public JPanel              getPanel()        { return panel; }
    public JButton             getCloseBtn()     { return closeBtn; }
    public JButton             getBackBtn()      { return backBtn; }
    public JComboBox<String>   getEmployeeList() { return employeeList; }
    public JComboBox<String>   getYearList()     { return yearList; }
}