package Helper;

import java.sql.*;

public class DBConnection {
	Connection c= null;
	
	public DBConnection() {
		
	}
	
	public Connection connDb() {
		try {
			this.c=DriverManager.getConnection("jdbc:mariadb://localhost:3306/cinema?user=root&password=12345");
		
			return c;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	

}
