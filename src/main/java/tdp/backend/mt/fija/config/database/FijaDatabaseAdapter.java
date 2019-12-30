package tdp.backend.mt.fija.config.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class FijaDatabaseAdapter implements DatabaseAdapter{
	static{
        try {
        	Class.forName("org.postgresql.Driver");
        } catch (Exception e) {}
    }

	@Override
	public Connection getConnection() {
		try {
			//jdbc:postgresql://localhost/prueba
            String connectionString = "jdbc:postgresql://localhost/dbsecurity";
            String user = "postgres";
            String password = "1234556";
            Connection connection = DriverManager.getConnection(connectionString, user, password);
            System.out.println("Connection db fija class => " + connection.getClass().getCanonicalName());
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
}
