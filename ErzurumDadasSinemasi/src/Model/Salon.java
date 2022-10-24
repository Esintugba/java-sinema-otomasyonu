package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Salon {
	
	private int id;
	private String name;
	
	DBConnection conn=new DBConnection();
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement preparedStatement=null;
	
	public boolean addSalon(String name) throws SQLException {
		
		String query="INSERT INTO salon"+"(id,name) VALUES"+"(?,?)";
		boolean key=false;
		Connection con =conn.connDb();
		
		try {
			st=con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2,name);
			preparedStatement.executeQuery();
			key=true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		if (key)
			return true;
		else
			return false;
	}
	
	public boolean deleteSalon(int id) throws SQLException {
		
		String query="DELETE FROM salon WHERE id = ? "; 
		boolean key=false;
		Connection con =conn.connDb();
		
		
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
	public boolean updateSalon(int id,String name) throws SQLException {
		
		String query="UPDATE salon SET name=?,id=?"; 
		boolean key=false;
		Connection con =conn.connDb();
		
		try {
			st=con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1,name);
			preparedStatement.setInt(2,id);
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
	public Salon() {
		
	}
	
	public ArrayList<Salon> getList() throws SQLException {
		ArrayList<Salon> list = new ArrayList<>();
		
		Salon obj;
		Connection con =conn.connDb();
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT*FROM salon");
			while(rs.next()) {
				obj=new Salon();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				list.add(obj);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			st.close();
			rs.close();
			con.close();
		}
		
	    return list;
		
	}
	
	public Salon getFetch(int id) {
		Connection con =conn.connDb();
		Salon s=new Salon();
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT*FROM salon WHERE id="+id);
			while(rs.next()) {
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				break;	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    return s;
		
	}
	
	public ArrayList<User> getSalonFilmList(int salon_id) throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		
		User obj;
	    try {
	    	Connection con =conn.connDb();
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
	
	public Salon(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
