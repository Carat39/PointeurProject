package utils;

import java.util.ArrayList;

/**
 * MODEL – Pour le moment il s'agit d'une "fausse" serialisation
 * pour commencer les test sans attendre la fin du backend.
 */
public class temp_serialisation {

    private ArrayList<String> employees;
    private ArrayList<String> availableYears;

    public temp_serialisation() {
        this.employees = new ArrayList<>();
        this.availableYears = new ArrayList<>();
    }

    public ArrayList<String> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<String> employees) {
        this.employees = employees;
    }

    public ArrayList<String> getAvailableYears() {
        return availableYears;
    }

    public void setAvailableYears(ArrayList<String> availableYears){
        this.availableYears = availableYears;
    }
}
