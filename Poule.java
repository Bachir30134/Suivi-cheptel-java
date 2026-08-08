public class Poule extends Animal {

    private double oeufsParJour;

    public Poule(String id, double poids, int age, double oeufsParJour) {
        super(id, poids, age);
        this.oeufsParJour = oeufsParJour;
    }

    @Override
    public String crier() {
        return "Cot cot";
    }

    @Override
    public double productionJournaliere() {
        return oeufsParJour;
    }

    @Override
    public String uniteProduction() {
        return "oeufs";
    }
}
