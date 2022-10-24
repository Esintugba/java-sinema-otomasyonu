package Model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class Yönetici extends User {
	Connection con =conn.connDb();
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement preparedStatement=null;
	
	
	
	public Yönetici(int id,String A,String B,String C,String D,String type) {
		super(id,A,B,C,D,type);
	}
	
	public Yönetici() {
		
	}
	
	public ArrayList<User> getFilmList() throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;
	    try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM user WHERE type='film'");
			while (rs.next()) {
				obj=new User(rs.getInt("id"),rs.getString("A"),rs.getString("B"),rs.getString("C"),rs.getString("D"),rs.getNString("type"));
				list.add(obj);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    return list;
	}
	
	public ArrayList<User> getSalonFilmList(int salon_id) throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		
		User obj;
	    try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT u.id,u.A,u.B,u.C,u.D,u.type FROM movie m LEFT JOIN user u ON m.user_id=u.id WHERE salon_id+'salon_id'");
			while (rs.next()) {
				obj=new User(rs.getInt("u.id"),rs.getString("u.A"),rs.getString("u.B"),rs.getString("u.C"),rs.getString("u.D"),rs.getNString("u.type"));
				list.add(obj);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    return list;
	    
		
		
	}
	
	public boolean addFilm(String A,String B,String C,String D) throws SQLException {
		
		String query="INSERT INTO user"+"(A,B,C,D,type) VALUES"+"(?,?,?,?,?)";
		boolean key=false;
		try {
			st=con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, A);
			preparedStatement.setString(2, B);
			preparedStatement.setString(3, C);
			preparedStatement.setString(4, D);
			preparedStatement.setString(5, "film");
			preparedStatement.executeUpdate();
			key=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}
	
    public boolean deleteFilm(int id) throws SQLException {
		
		String query="DELETE FROM user WHERE id = ? "; 
		boolean key=false;
		try {
			st=con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key=true;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}
    
    public boolean updateFilm(int id,String A,String B,String C,String D) throws SQLException {
		
		String query= "UPDATE user SET A=?,B=?,C=?,D=? WHERE id=?"; 
		boolean key=false;
		try {
			st=con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1,A);
			preparedStatement.setString(2,B);
			preparedStatement.setString(3,C);
			preparedStatement.setString(4,D);
			preparedStatement.setInt(5,id);
			preparedStatement.executeUpdate();
			key=true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		if (key)
			return true;
		else
			return false;
	}

	
	
	public boolean addMovie(int user_id,int salon_id) throws SQLException {
		
		String query="INSERT INTO movie" + "(user_id,salon_id) VALUES" + "(?,?)";
		boolean key=false;
	
		int count=0;
		
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM movie WHERE salon_id = + 'salon_id'"+"AND user_id= + 'user_id'");
			while (rs.next()) {
				count++;
			}
			if (count==0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1,user_id);
				preparedStatement.setInt(2,salon_id);
				preparedStatement.executeUpdate();
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


 