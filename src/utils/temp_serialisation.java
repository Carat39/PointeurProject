package utils;

import java.util.ArrayList;

/**
 * MODEL – Contient uniquement les données de l'application.
 * Les données (employés, années) seront injectées via setters
 * après désérialisation. Aucune valeur codée en dur ici.
 */
public class temp_serialisation {

    private ArrayList<String> employees;
    private ArrayList<String> availableYears;

    public temp_serialisation() {
        this.employees = new ArrayList<>();
        this.availableYears = new ArrayList<>();
    }

    // --- Employés ---

    public ArrayList<String> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<String> employees) {
        this.employees = employees;
    }

    // --- Années ---

    public ArrayList<String> getAvailableYears() {
        return availableYears;
    }

    public void setAvailableYears(ArrayList<String> availableYears) {
        this.availableYears = availableYears;
    }
}
