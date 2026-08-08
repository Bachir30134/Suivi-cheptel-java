public class Main {

    public static void main(String[] args) {

        Elevage elevage = new Elevage();

        // Création d'au moins 5 animaux
        elevage.ajouter(new Vache("V1", 550, 36, 18.5));
        elevage.ajouter(new Vache("V2", 600, 48, 20.0));
        elevage.ajouter(new Poule("P1", 2.1, 8, 1));
        elevage.ajouter(new Poule("P2", 2.3, 10, 0.8));
        elevage.ajouter(new Mouton("M1", 60, 24, 150));

        // Vaccination partielle avant le bilan
        elevage.getAnimaux().get(0).vacciner(); // V1 vaccinée
        elevage.getAnimaux().get(2).vacciner(); // P1 vaccinée

        elevage.afficherTous();
        System.out.println();

        elevage.afficherProductionTotale();
        System.out.println();

        elevage.listerNonVaccines();
        System.out.println();

        System.out.println(">>> Vaccination de tout le cheptel <<<");
        elevage.vaccinerTous();
        System.out.println();

        elevage.listerNonVaccines();
    }
}
