

import net.antidot.sql.model.core.DriverType;

public interface Settings {
			
	// MySQL Database TEST settings
	public static final String userName = "root";
	public static final String password = "root";
	public static final DriverType driver = DriverType.MysqlDriver;
	public static final String url = "jdbc:mysql://localhost/";
	public static final String testDbName = "testing";
	public static final String dbName = "mysql";

	// PostgreSQL Database TEST settings
//	public static final String userName = "root";
//	public static final String password = "root";
//	public static final DriverType driver = DriverType.PostgreSQL;
//	public static final String testDbName = "test";
//	public static final String url = "jdbc:postgresql://127.0.0.1:5432/";
//	public static final String dbName = "postgresql";
}
