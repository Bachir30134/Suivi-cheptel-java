import java.sql.SQLException;
import java.util.List;

/**
 * Version du programme qui passe par MySQL au lieu de la liste en mémoire.
 * Compare-la à Main.java : la logique métier (Elevage, polymorphisme) ne change pas,
 * seule la façon de récupérer/sauver les animaux change.
 */
public class MainBD {

    public static void main(String[] args) {

        AnimalDAO dao = new AnimalDAO();

        try {
            // 1. Charger tous les animaux déjà présents en base
            List<Animal> animaux = dao.chargerTous();

            Elevage elevage = new Elevage();
            for (Animal a : animaux) {
                elevage.ajouter(a);
            }

            System.out.println("=== Animaux chargés depuis MySQL ===");
            elevage.afficherTous();
            System.out.println();

            elevage.afficherProductionTotale();
            System.out.println();

            elevage.listerNonVaccines();
            System.out.println();

            // 2. Ajouter un nouvel animal et le sauvegarder en base
            Animal nouveau = new Mouton("M2", 55, 12, 120);
            elevage.ajouter(nouveau);
            dao.inserer(nouveau);
            System.out.println("Nouvel animal M2 ajouté et sauvegardé en base.");
            System.out.println();

            // 3. Vacciner tout le monde : en mémoire ET en base
            System.out.println(">>> Vaccination de tout le cheptel <<<");
            elevage.vaccinerTous();
            dao.vaccinerTous();

            elevage.listerNonVaccines();

        } catch (SQLException e) {
            System.out.println("Erreur de connexion ou de requête MySQL :");
            e.printStackTrace();
        }
    }
}
