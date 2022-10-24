package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Seans {
	DBConnection conn=new DBConnection();
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement preparedStatement=null;
	
	
	private int id,film_id;
	private String film_isim,seans,rezervasyon;
	
	public Seans() {
		
	}
	
	
	public Seans(int id, int film_id, String film_isim, String seans, String rezervasyon) {
		super();
		this.id = id;
		this.film_id = film_id;
		this.film_isim = film_isim;
		this.seans = seans;
		this.rezervasyon = rezervasyon;
	}
	public ArrayList<Seans> getSeansList(int film_id) throws SQLException {
		ArrayList<Seans> list = new ArrayList<>();
		
		Seans obj;
	    try {
	    	Connection con =conn.connDb();
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
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFilm_id() {
		return film_id;
	}
	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}
	public String getFilm_isim() {
		return film_isim;
	}
	public void setFilm_isim(String film_isim) {
		this.film_isim = film_isim;
	}
	public String getSeans() {
		return seans;
	}
	public void setSeans(String seans) {
		this.seans = seans;
	}
	public String getRezervasyon() {
		return rezervasyon;
	}
	public void setRezervasyon(String rezervasyon) {
		this.rezervasyon = rezervasyon;
	}
	
	

}
