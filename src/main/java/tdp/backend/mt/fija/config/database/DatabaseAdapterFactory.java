package tdp.backend.mt.fija.config.database;

public class DatabaseAdapterFactory {
	public static DatabaseAdapter getAdapter(DatabaseType type){
        switch (type){
            case MT:
                return new MtDatabaseAdapter();
            case FIJA:
                return new FijaDatabaseAdapter();
            default:
                return null;
        }
    }
	public static DatabaseAdapter getAdapter(String type){
		switch (type){
		case "MT":
			return new MtDatabaseAdapter();
		case "FIJA":
			return new FijaDatabaseAdapter();
		default:
			return null;
		}
	}

}
