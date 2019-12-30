package tdp.backend.mt.fija;

import java.sql.Connection;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import tdp.backend.mt.fija.config.database.DatabaseAdapter;
import tdp.backend.mt.fija.config.database.DatabaseAdapterFactory;
import tdp.backend.mt.fija.config.database.DatabaseType;
import tdp.backend.mt.fija.config.database.FijaDatabaseAdapter;
import tdp.backend.mt.fija.config.database.MtDatabaseAdapter;

@SpringBootTest
class BackendMtFijaApplicationTests {

	@Test
	void contextLoads() {
//		MtDatabaseAdapter mt = new MtDatabaseAdapter();
//		Connection conn = mt.getConnection();
//		
//		FijaDatabaseAdapter fijadb = new FijaDatabaseAdapter();
//		Connection conn2 = fijadb.getConnection();
		String dbFija = "FIJA";
		
		
		DatabaseAdapter adapter = DatabaseAdapterFactory.getAdapter(dbFija);
		Connection conn3 = adapter.getConnection();
	}

}
