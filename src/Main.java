import pointingApplication.Pointing;

void main() throws Exception{

    IO.println(String.format("Hello and welcome!"));

    for (int i = 1; i <= 5; i++) {
        IO.println("on m'appelle l'ovni = " + i);
    }

    /*Pointing p = new Pointing;
    p.setStartHour();
    LocalTime heuretest= p.getStartHour();
    System.out.println("Il est : " + heuretest);
    heuretest= p.round(heuretest);
    System.out.println("Arrondis à la quinzaine : " + heuretest);
    */
    Pointing.serialisationtest();
}
