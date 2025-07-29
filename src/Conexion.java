import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://bf9tdnqqsowpnjoxgpxg-mysql.services.clever-cloud.com:3306/bf9tdnqqsowpnjoxgpxg?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USUARIO = "uwpjygda5yd3mvfo";
    private static final String CLAVE = "hllUoQ08tzU4ZYbYggdo";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }
}

