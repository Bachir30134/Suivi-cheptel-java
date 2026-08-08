public class Mouton extends Animal {

    private double laineParJour; // en grammes

    public Mouton(String id, double poids, int age, double laineParJour) {
        super(id, poids, age);
        this.laineParJour = laineParJour;
    }

    @Override
    public String crier() {
        return "Bêê";
    }

    @Override
    public double productionJournaliere() {
        return laineParJour;
    }

    @Override
    public String uniteProduction() {
        return "g de laine";
    }
}
