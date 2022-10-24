package View;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UIManager;
import Helper.*;
import Model.Film;
import Model.M�steri;
import Model.Y�netici;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;


public class LoginGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_m�steriad;
	private JTextField fld_m�steritelefon;
	private JTextField fld_m�sterimail;
	private JPasswordField fld_m�sterisifre;
	private JTextField fld_y�neticiad;
	private JTextField fld_y�neticitelefon;
	private JTextField fld_y�neticimail;
	private JPasswordField fld_y�neticisifre;
	private DBConnection conn=new DBConnection();
	private M�steri m�steri=new M�steri();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("ERZURUM DADA\u015E S\u0130NEMASI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 491);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(0, 0, 0));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("sinema.png")));
		lbl_logo.setBounds(27, 160, 142, 147);
		w_pane.add(lbl_logo);
		
		JLabel lbl_slgn = new JLabel("ERZURUM DADA\u015E S\u0130NEMASINA HO\u015EGELD\u0130N\u0130Z");
		lbl_slgn.setBounds(61, 11, 474, 48);
		lbl_slgn.setForeground(new Color(255, 255, 255));
		lbl_slgn.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		w_pane.add(lbl_slgn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(193, 70, 367, 369);
		w_pane.add(tabbedPane);
		
		JPanel w_m�steri = new JPanel();
		w_m�steri.setBackground(new Color(204, 102, 153));
		tabbedPane.addTab("M��teri Giri�i", null, w_m�steri, null);
		w_m�steri.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AD-SOYAD:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(17, 37, 70, 31);
		w_m�steri.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("TELEFON:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1.setBounds(19, 93, 66, 31);
		w_m�steri.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("E-MA\u0130L:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_2.setBounds(27, 146, 46, 34);
		w_m�steri.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u015E\u0130FRE:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_3.setBounds(27, 205, 46, 34);
		w_m�steri.add(lblNewLabel_3);
		
		fld_m�steriad = new JTextField();
		fld_m�steriad.setBackground(new Color(255, 255, 255));
		fld_m�steriad.setBounds(97, 34, 235, 34);
		w_m�steri.add(fld_m�steriad);
		fld_m�steriad.setColumns(10);
		
		fld_m�steritelefon = new JTextField();
		fld_m�steritelefon.setBounds(97, 90, 235, 34);
		w_m�steri.add(fld_m�steritelefon);
		fld_m�steritelefon.setColumns(10);
		
		fld_m�sterimail = new JTextField();
		fld_m�sterimail.setBounds(97, 143, 235, 34);
		w_m�steri.add(fld_m�sterimail);
		fld_m�sterimail.setColumns(10);
		
		fld_m�sterisifre = new JPasswordField();
		fld_m�sterisifre.setBounds(97, 205, 235, 34);
		w_m�steri.add(fld_m�sterisifre);
		
		JButton btn_m�sterikayit = new JButton("Kay\u0131t Ol");
		btn_m�sterikayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_m�steriad.getText().length()==0 || fld_m�steritelefon.getText().length()==0 || fld_m�sterimail.getText().length()==0 || fld_m�sterisifre.getText().length()==0) {
				Helper.showMsg("fill");
			    }
				else {
					try {
						boolean control =m�steri.addM�steri(fld_m�steriad.getText(),fld_m�steritelefon.getText(),fld_m�sterimail.getText(),fld_m�sterisifre.getText());
						if (control) {
							Helper.showMsg("Kayd�n�z Olu�turulmu�tur");
							BiletGUI bGUI=new BiletGUI(m�steri);
							bGUI.setVisible(true);
							dispose();
		
						}
						else {
							Helper.showMsg("error");
						}
							
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
		  }
			
		});
		
		btn_m�sterikayit.setBackground(UIManager.getColor("Button.shadow"));
		btn_m�sterikayit.setBounds(112, 271, 89, 45);
		w_m�steri.add(btn_m�sterikayit);
		
		JButton btn_m�sterigiris = new JButton("Giri\u015F Yap");
		btn_m�sterigiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_m�steriad.getText().length()==0 || fld_m�steritelefon.getText().length()==0 || fld_m�sterimail.getText().length()==0 || fld_m�sterisifre.getText().length()==0) {
				Helper.showMsg("fill");	
				}else {
					try {
						boolean control=m�steri.addM�steri(fld_m�steriad.getText(),fld_m�steritelefon.getText(),fld_m�sterimail.getText(),fld_m�sterisifre.getText());
						if (control) {
							Helper.showMsg("Ba�ar�l� Giri�");
							BiletGUI bGUI=new BiletGUI(m�steri);
							bGUI.setVisible(true);
							dispose();
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
				
			}
			
		});
		btn_m�sterigiris.setBackground(UIManager.getColor("Button.shadow"));
		btn_m�sterigiris.setBounds(233, 271, 89, 45);
		w_m�steri.add(btn_m�sterigiris);
		
		JPanel w_y�netici = new JPanel();
		w_y�netici.setBackground(new Color(204, 102, 153));
		tabbedPane.addTab("Y�netici Giri�", null, w_y�netici, null);
		w_y�netici.setLayout(null);
		
		JLabel lbl_y�neticiad = new JLabel("AD-SOYAD:");
		lbl_y�neticiad.setForeground(new Color(255, 255, 255));
		lbl_y�neticiad.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbl_y�neticiad.setBounds(17, 37, 70, 31);
		w_y�netici.add(lbl_y�neticiad);
		
		JLabel lbl_y�neticitelefon = new JLabel("TELEFON:");
		lbl_y�neticitelefon.setForeground(new Color(255, 255, 255));
		lbl_y�neticitelefon.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbl_y�neticitelefon.setBounds(19, 93, 66, 31);
		w_y�netici.add(lbl_y�neticitelefon);
		
		JLabel lbl_y�neticimail = new JLabel("E-MA\u0130L:");
		lbl_y�neticimail.setForeground(new Color(255, 255, 255));
		lbl_y�neticimail.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbl_y�neticimail.setBounds(27, 146, 46, 34);
		w_y�netici.add(lbl_y�neticimail);
		
		JLabel lbl_y�neticisifre = new JLabel("\u015E\u0130FRE:");
		lbl_y�neticisifre.setForeground(new Color(255, 255, 255));
		lbl_y�neticisifre.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbl_y�neticisifre.setBounds(27, 205, 46, 34);
		w_y�netici.add(lbl_y�neticisifre);
		
		fld_y�neticiad = new JTextField();
		fld_y�neticiad.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		fld_y�neticiad.setBounds(97, 34, 235, 34);
		w_y�netici.add(fld_y�neticiad);
		fld_y�neticiad.setColumns(10);
		
		fld_y�neticitelefon = new JTextField();
		fld_y�neticitelefon.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		fld_y�neticitelefon.setBounds(97, 90, 235, 34);
		w_y�netici.add(fld_y�neticitelefon);
		fld_y�neticitelefon.setColumns(10);
		
		fld_y�neticimail = new JTextField();
		fld_y�neticimail.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		fld_y�neticimail.setBounds(97, 143, 235, 34);
		w_y�netici.add(fld_y�neticimail);
		fld_y�neticimail.setColumns(10);
		
		fld_y�neticisifre = new JPasswordField();
		fld_y�neticisifre.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		fld_y�neticisifre.setBounds(97, 205, 235, 34);
		w_y�netici.add(fld_y�neticisifre);
		
		JButton btn_y�neticiLogin = new JButton("Giri\u015F Yap");
		btn_y�neticiLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_y�neticiad.getText().length()==0 || fld_y�neticitelefon.getText().length()==0 || fld_y�neticimail.getText().length()==0 || fld_y�neticisifre.getText().length()==0) {
					Helper.showMsg("fill");		
				}
				else {
					try {
						Connection con= conn.connDb();
						Statement st=con.createStatement();
						ResultSet rs=st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_y�neticiad.getText().equals(rs.getString("A")) && fld_y�neticitelefon.getText().equals(rs.getString("B")) && fld_y�neticimail.getText().equals(rs.getString("C")) && fld_y�neticisifre.getText().equals(rs.getString("D"))) {
								if(rs.getString("type").equals("y�netici")) {
									Y�netici ynetici=new Y�netici();
									ynetici.setId(rs.getInt("id"));
									ynetici.setA(rs.getString("A"));
									ynetici.setB(rs.getString("B"));
									ynetici.setC(rs.getString("C"));
									ynetici.setD(rs.getString("D"));
									ynetici.setType(rs.getString("type"));
									Y�neticiGUI yGUI=new Y�neticiGUI(ynetici);
									yGUI.setVisible(true);
									dispose();
								}
								if(rs.getString("type").equals("m�steri")) {
									M�steri m�steri=new M�steri();
									m�steri.setId(rs.getInt("id"));
									m�steri.setA(rs.getString("A"));
									m�steri.setB(rs.getString("B"));
									m�steri.setC(rs.getString("C"));
									m�steri.setD(rs.getString("D"));
									m�steri.setType(rs.getString("type"));
									BiletGUI bGUI=new BiletGUI(m�steri);
									bGUI.setVisible(true);
									dispose();

								}
							}
						}
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
				
			}
		});
		btn_y�neticiLogin.setForeground(new Color(0, 0, 0));
		btn_y�neticiLogin.setBackground(UIManager.getColor("Button.shadow"));
		btn_y�neticiLogin.setBounds(233, 271, 89, 45);
		w_y�netici.add(btn_y�neticiLogin);
	}

}
