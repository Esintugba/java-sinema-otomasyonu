package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Müsteri extends User {
	Connection con =conn.connDb();
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement preparedStatement=null;
	
	public Müsteri(int id,String A,String B,String C,String D,String type) {
		super(id,A,B,C,D,type);
	}
	
	public Müsteri() {
		
	}
	
	public boolean addMüsteri(String A,String B,String C,String D) throws SQLException {
		
		String query="INSERT INTO user"+"(A,B,C,D,type) VALUES"+"(?,?,?,?,?)";
		boolean key=true;
		boolean tekrar=false;
		Connection con =conn.connDb();
		
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM user WHERE B='"+B+"'");
			while(rs.next()) {
				tekrar=true;
				break;
			}
			
			if (!tekrar) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1,A);
				preparedStatement.setString(2,B);
				preparedStatement.setString(3,C);
				preparedStatement.setString(4,D);
				preparedStatement.setString(5,"müsteri");
				
				preparedStatement.executeQuery();
				key=true;
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		if (key)
			return true;
		else
			return false;
	}
	
}	
