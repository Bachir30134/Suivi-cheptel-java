public class Vache extends Animal {

    private double litresParJour;

    public Vache(String id, double poids, int age, double litresParJour) {
        super(id, poids, age);
        this.litresParJour = litresParJour;
    }

    @Override
    public String crier() {
        return "Meuh";
    }

    @Override
    public double productionJournaliere() {
        return litresParJour;
    }

    @Override
    public String uniteProduction() {
        return "L de lait";
    }
}
