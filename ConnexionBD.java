import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Centralise la connexion à la base MySQL.
 * Les identifiants (url, user, password) sont lus depuis config.properties,
 * un fichier qui reste sur ton PC et n'est JAMAIS envoyé sur GitHub
 * (voir .gitignore).
 */
public class ConnexionBD {

    private static final String FICHIER_CONFIG = "config.properties";

    public static Connection getConnection() throws SQLException {
        Properties props = chargerConfig();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }

    private static Properties chargerConfig() throws SQLException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(FICHIER_CONFIG)) {
            props.load(fis);
        } catch (IOException e) {
            throw new SQLException(
                "Impossible de lire " + FICHIER_CONFIG + ". "
                + "Copie config.properties.example vers config.properties "
                + "et renseigne ton mot de passe MySQL.", e);
        }
        return props;
    }
}
