package tdp.backend.mt.fija.config.database;

import java.sql.Connection;

public interface DatabaseAdapter {
	public Connection getConnection();
}
