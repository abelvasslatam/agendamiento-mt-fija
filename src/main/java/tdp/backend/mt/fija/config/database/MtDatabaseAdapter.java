package tdp.backend.mt.fija.config.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class MtDatabaseAdapter implements DatabaseAdapter{
	
	static{
        try {
        	Class.forName("org.postgresql.Driver");
        } catch (Exception e) {}
    }

	@Override
	public Connection getConnection() {
		try {
            String connectionString = "jdbc:postgresql://localhost/prueba";
            String user = "postgres";
            String password = "1234556";
            Connection connection = DriverManager.getConnection(connectionString, user, password);
            System.out.println("Connection db mt class => " + connection.getClass().getCanonicalName());
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}

}
