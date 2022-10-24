package Model;

import Helper.DBConnection;

public class User {
	private int id;
	private String A,B,C,D,type;
	
	DBConnection conn=new DBConnection();
	
	public User(int id, String A, String B, String C, String D, String type) {
		super();
		this.id = id;
		this.A= A;
		this.B = B;
		this.C = C;
		this.D = D;
		this.type = type;
	}
	public User() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getA() {
		return A;
	}
	public void setA(String A) {
		this.A = A;
	}
	public String getB() {
		return B;
	}
	public void setB(String B) {
		this.B = B;
	}
	public String getC() {
		return C;
	}
	public void setC(String C) {
		this.C = C;
	}
	public String getD() {
		return D;
	}
	public void setD(String D) {
		this.D = D;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
