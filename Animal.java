/**
 * Classe abstraite représentant un animal générique de la ferme.
 * Toute sous-classe doit définir son cri et sa production journalière.
 */
public abstract class Animal implements Vaccinable {

    protected String id;
    protected double poids;   // en kg
    protected int age;        // en mois
    protected boolean vaccine = false;

    public Animal(String id, double poids, int age) {
        this.id = id;
        this.poids = poids;
        this.age = age;
    }

    // Méthodes abstraites : chaque espèce doit fournir sa propre version
    public abstract String crier();
    public abstract double productionJournaliere();

    // Méthode "unite" pour savoir dans quelle unité s'exprime la production
    // (utile pour l'affichage par espèce, cf. remarque du sujet sur les unités)
    public abstract String uniteProduction();

    // Implémentation commune de l'interface Vaccinable
    @Override
    public boolean estVaccine() {
        return vaccine;
    }

    @Override
    public void vacciner() {
        this.vaccine = true;
    }

    public String getId() {
        return id;
    }

    public double getPoids() {
        return poids;
    }

    public int getAge() {
        return age;
    }

    public void afficher() {
        System.out.printf("%s | %s | poids=%.1fkg | age=%dmois | prod=%.1f %s | vaccine=%b%n",
                id, crier(), poids, age, productionJournaliere(), uniteProduction(), vaccine);
    }
}
