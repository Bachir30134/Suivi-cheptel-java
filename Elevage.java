import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Représente la ferme : contient tous les animaux et gère les opérations
 * globales (production, vaccination).
 */
public class Elevage {

    private List<Animal> animaux = new ArrayList<>();

    public void ajouter(Animal a) {
        animaux.add(a);
    }

    public List<Animal> getAnimaux() {
        return animaux;
    }

    /**
     * Les unités de production sont différentes selon l'espèce
     * (litres, oeufs, grammes) : on ne fait donc pas un total unique,
     * mais un total par unité de production, obtenu par parcours polymorphe.
     */
    public Map<String, Double> productionParUnite() {
        Map<String, Double> totaux = new LinkedHashMap<>();
        for (Animal a : animaux) {
            String unite = a.uniteProduction();
            double valeur = a.productionJournaliere(); // appel polymorphe
            totaux.merge(unite, valeur, Double::sum);
        }
        return totaux;
    }

    public void afficherProductionTotale() {
        System.out.println("=== Production journalière totale par unité ===");
        for (Map.Entry<String, Double> entry : productionParUnite().entrySet()) {
            System.out.printf("- %.1f %s%n", entry.getValue(), entry.getKey());
        }
    }

    public void listerNonVaccines() {
        System.out.println("=== Animaux non vaccinés ===");
        boolean aucun = true;
        for (Animal a : animaux) {
            if (!a.estVaccine()) {
                a.afficher();
                aucun = false;
            }
        }
        if (aucun) {
            System.out.println("(aucun)");
        }
    }

    public void vaccinerTous() {
        for (Animal a : animaux) {
            a.vacciner();
        }
    }

    public void afficherTous() {
        System.out.println("=== Bilan de tous les animaux ===");
        for (Animal a : animaux) {
            a.afficher();
        }
    }
}
