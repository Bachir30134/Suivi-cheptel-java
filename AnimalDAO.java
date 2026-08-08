import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * AnimalDAO = "Data Access Object".
 * Son unique rôle : traduire les objets Animal en lignes SQL et inversement.
 * Le reste du programme (Elevage, Main...) n'a pas besoin de connaître le SQL.
 */
public class AnimalDAO {

    // ---------- INSERER ----------

    public void inserer(Animal a) throws SQLException {
    String type = determinerType(a);
    String sql = "INSERT INTO animal (id, type, poids, age, production, vaccine) "
               + "VALUES (?, ?, ?, ?, ?, ?) "
               + "ON DUPLICATE KEY UPDATE "
               + "poids = VALUES(poids), age = VALUES(age), "
               + "production = VALUES(production), vaccine = VALUES(vaccine)";

    try (Connection conn = ConnexionBD.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, a.getId());
        stmt.setString(2, type);
        stmt.setDouble(3, a.getPoids());
        stmt.setInt(4, a.getAge());
        stmt.setDouble(5, a.productionJournaliere());
        stmt.setBoolean(6, a.estVaccine());

        stmt.executeUpdate();
    }
}

    // ---------- CHARGER TOUS LES ANIMAUX ----------

    public List<Animal> chargerTous() throws SQLException {
        List<Animal> animaux = new ArrayList<>();
        String sql = "SELECT id, type, poids, age, production, vaccine FROM animal";

        try (Connection conn = ConnexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                animaux.add(construireAnimal(rs));
            }
        }
        return animaux;
    }

    // Reconstitue le bon objet (Vache, Poule ou Mouton) selon la colonne "type"
    private Animal construireAnimal(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String type = rs.getString("type");
        double poids = rs.getDouble("poids");
        int age = rs.getInt("age");
        double production = rs.getDouble("production");
        boolean vaccine = rs.getBoolean("vaccine");

        Animal a;
        switch (type) {
            case "VACHE"  -> a = new Vache(id, poids, age, production);
            case "POULE"  -> a = new Poule(id, poids, age, production);
            case "MOUTON" -> a = new Mouton(id, poids, age, production);
            default -> throw new IllegalStateException("Type d'animal inconnu en base : " + type);
        }
        if (vaccine) {
            a.vacciner();
        }
        return a;
    }

    // ---------- METTRE A JOUR LA VACCINATION ----------

    public void mettreAJourVaccination(String id, boolean vaccine) throws SQLException {
        String sql = "UPDATE animal SET vaccine = ? WHERE id = ?";

        try (Connection conn = ConnexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, vaccine);
            stmt.setString(2, id);
            stmt.executeUpdate();
        }
    }

    public void vaccinerTous() throws SQLException {
        String sql = "UPDATE animal SET vaccine = TRUE";
        try (Connection conn = ConnexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
    }

    // ---------- OUTIL ----------

    private String determinerType(Animal a) {
        if (a instanceof Vache) return "VACHE";
        if (a instanceof Poule) return "POULE";
        if (a instanceof Mouton) return "MOUTON";
        throw new IllegalArgumentException("Type d'animal non géré : " + a.getClass());
    }
}
