package views;

import mainApplication.management.Departement;
import mainApplication.management.Staff;
import views.utils.Variables;

import javax.swing.*;
import java.awt.*;

/**
 * VIEW – Page « Edit Employee »
 * C'est la page qui nous permet de modifier les informations d'un employé, d'en creer un nouveau
 * ou d'en supprimer un.
 */
public class Page5 {

    private final JPanel panel;
    private final JButton closeBtn;
    private final JButton backBtn;
    private final JButton saveBtn;
    private final JButton deleteBtn;
    private final JButton createBtn;

    private final JTextField nameField;
    private final JTextField surnameField;
    private final JComboBox<Departement> deptCombo;

    private Staff staffRef;

    public Page5() {
        panel = new JPanel(null);
        panel.setBackground(Variables.PASTEL_YELLOW);

        closeBtn = Variables.createCloseBtn();
        backBtn = Variables.createBackBtn();


        Font customFont = new Font("Lucida Handwriting", Font.PLAIN, 14);
        Font labelFont = new Font("Lucida Handwriting", Font.BOLD, 13);

        int centerX = Variables.WIDTH / 2;
        int topY = Variables.HEIGHT / 3;

        JLabel nameLabel = new JLabel("Name :");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(Color.DARK_GRAY);

        JLabel surnameLabel = new JLabel("Surname :");
        surnameLabel.setFont(labelFont);
        surnameLabel.setForeground(Color.DARK_GRAY);

        JLabel deptLabel = new JLabel("Departement :");
        deptLabel.setFont(labelFont);
        deptLabel.setForeground(Color.DARK_GRAY);

        nameField = new JTextField();
        nameField.setFont(customFont);
        nameField.setBackground(Color.WHITE);

        surnameField = new JTextField();
        surnameField.setFont(customFont);
        surnameField.setBackground(Color.WHITE);

        deptCombo = new JComboBox<>(Departement.values());
        deptCombo.setFont(customFont);
        deptCombo.setBackground(Color.WHITE);

        nameLabel.setBounds(centerX - 180, topY, 130, 30);
        nameField.setBounds(centerX - 30, topY, 210, 30);

        surnameLabel.setBounds(centerX - 180, topY + 50, 130, 30);
        surnameField.setBounds(centerX - 30, topY + 50, 210, 30);

        deptLabel.setBounds(centerX - 180, topY + 100, 130, 30);
        deptCombo.setBounds(centerX - 30, topY + 100, 210, 30);

        int buttonsY = topY + 170;
        int btnWidth = 135;
        int btnHeight = 40;

        deleteBtn = new JButton("Delete");
        deleteBtn.setFont(customFont);
        deleteBtn.setBackground(Variables.PASTEL_PEACH);
        deleteBtn.setForeground(Color.DARK_GRAY);
        deleteBtn.setFocusPainted(false);
        deleteBtn.setBounds(centerX - 215, buttonsY, btnWidth, btnHeight);


        saveBtn = new JButton("Save");
        saveBtn.setFont(customFont);
        saveBtn.setBackground(Variables.PASTEL_PEACH);
        saveBtn.setForeground(Color.DARK_GRAY);
        saveBtn.setFocusPainted(false);
        saveBtn.setBounds(centerX - 67, buttonsY, btnWidth, btnHeight);


        createBtn = new JButton("Create");
        createBtn.setFont(customFont);
        createBtn.setBackground(Variables.PASTEL_PEACH);
        createBtn.setForeground(Color.DARK_GRAY);
        createBtn.setFocusPainted(false);
        createBtn.setBounds(centerX + 80, buttonsY, btnWidth, btnHeight);


        panel.add(Variables.createTitle("Manage Employee"));
        panel.add(closeBtn);
        panel.add(backBtn);
        panel.add(saveBtn);
        panel.add(deleteBtn);
        panel.add(createBtn);

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(surnameLabel);
        panel.add(surnameField);
        panel.add(deptLabel);
        panel.add(deptCombo);
    }


    public void loadStaff(Staff staff) {
        staffRef = staff;
        nameField.setText(staff.getname());
        surnameField.setText(staff.getsurname());
        deptCombo.setSelectedItem(staff.getdepartement());

    }


    public JPanel getPanel() {
        return panel;
    }

    public JButton getCloseBtn() {
        return closeBtn;
    }

    public JButton getBackBtn() {
        return backBtn;
    }

    public JButton getSaveBtn() {
        return saveBtn;
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getSurnameField() {
        return surnameField;
    }

    public JComboBox<Departement> getDeptCombo() {
        return deptCombo;
    }

    public Staff getStaffRef() {
        return staffRef;
    }

    public JButton getCreateBtn(){return createBtn;}
}