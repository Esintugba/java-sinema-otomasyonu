package Model;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Film extends User {
	Connection con =conn.connDb();
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement preparedStatement=null;
	
	public Film() {
		super();
	}
	public Film(int id, String A, String B, String C, String D, String type) {
		super(id, A, B, C, D, type);
	}
	
	public boolean addGösterimSaat(int film_id,String film_isim,String tarih) throws SQLException {
		int key=0;
		int count=0;
		
		String query="INSERT INTO ftime" + "(film_id,film_isim,tarih) VALUES " +"(?,?,?)";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM ftime WHERE rezervasyon='Boþ'"+ "AND film_id="+film_id);
			
			while(rs.next()) {
				count++;
				break;
			}
			if (count==0) {
				preparedStatement=con.prepareStatement(query);
				preparedStatement.setInt(1,film_id);
				preparedStatement.setString(2,film_isim);
				preparedStatement.setString(3, tarih);
	
			}
			key=1;
			
			if(key==1)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	
	}
	public ArrayList<Seans> getSeansList(int film_id) throws SQLException {
		ArrayList<Seans> list = new ArrayList<>();
		
		Seans obj;
	    try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM ftime WHERE rezervasyon='Boþ' AND film_id = "+ film_id);
			while (rs.next()) {
				obj=new Seans();
				obj.setId(rs.getInt("id"));
				obj.setFilm_id(rs.getInt(film_id));
				obj.setFilm_isim(rs.getString("film_isim"));
				obj.setRezervasyon(rs.getString("rezervasyon"));
				obj.setSeans(rs.getString("seans"));
				
				list.add(obj);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    return list;
	
	}
	
	public boolean deleteSeans(int id) throws SQLException {
		
		String query="DELETE FROM ftime WHERE id = ? "; 
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
	public ArrayList<Film> getList() throws SQLException {
		ArrayList<Film> list = new ArrayList<>();
		
		Film obj;
		Connection con =conn.connDb();
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT*FROM user");
			while(rs.next()) {
				obj=new Film();
				obj.setId(rs.getInt("id"));
				obj.setA(rs.getString("A"));
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
	
	
	

}
