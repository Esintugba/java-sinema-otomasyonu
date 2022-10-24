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
import Model.Müsteri;
import Model.Yönetici;

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
	private JTextField fld_müsteriad;
	private JTextField fld_müsteritelefon;
	private JTextField fld_müsterimail;
	private JPasswordField fld_müsterisifre;
	private JTextField fld_yöneticiad;
	private JTextField fld_yöneticitelefon;
	private JTextField fld_yöneticimail;
	private JPasswordField fld_yöneticisifre;
	private DBConnection conn=new DBConnection();
	private Müsteri müsteri=new Müsteri();

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
		
		JPanel w_müsteri = new JPanel();
		w_müsteri.setBackground(new Color(204, 102, 153));
		tabbedPane.addTab("Müþteri Giriþi", null, w_müsteri, null);
		w_müsteri.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AD-SOYAD:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(17, 37, 70, 31);
		w_müsteri.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("TELEFON:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1.setBounds(19, 93, 66, 31);
		w_müsteri.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("E-MA\u0130L:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_2.setBounds(27, 146, 46, 34);
		w_müsteri.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u015E\u0130FRE:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_3.setBounds(27, 205, 46, 34);
		w_müsteri.add(lblNewLabel_3);
		
		fld_müsteriad = new JTextField();
		fld_müsteriad.setBackground(new Color(255, 255, 255));
		fld_müsteriad.setBounds(97, 34, 235, 34);
		w_müsteri.add(fld_müsteriad);
		fld_müsteriad.setColumns(10);
		
		fld_müsteritelefon = new JTextField();
		fld_müsteritelefon.setBounds(97, 90, 235, 34);
		w_müsteri.add(fld_müsteritelefon);
		fld_müsteritelefon.setColumns(10);
		
		fld_müsterimail = new JTextField();
		fld_müsterimail.setBounds(97, 143, 235, 34);
		w_müsteri.add(fld_müsterimail);
		fld_müsterimail.setColumns(10);
		
		fld_müsterisifre = new JPasswordField();
		fld_müsterisifre.setBounds(97, 205, 235, 34);
		w_müsteri.add(fld_müsterisifre);
		
		JButton btn_müsterikayit = new JButton("Kay\u0131t Ol");
		btn_müsterikayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_müsteriad.getText().length()==0 || fld_müsteritelefon.getText().length()==0 || fld_müsterimail.getText().length()==0 || fld_müsterisifre.getText().length()==0) {
				Helper.showMsg("fill");
			    }
				else {
					try {
						boolean control =müsteri.addMüsteri(fld_müsteriad.getText(),fld_müsteritelefon.getText(),fld_müsterimail.getText(),fld_müsterisifre.getText());
						if (control) {
							Helper.showMsg("Kaydýnýz Oluþturulmuþtur");
							BiletGUI bGUI=new BiletGUI(müsteri);
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
		
		btn_müsterikayit.setBackground(UIManager.getColor("Button.shadow"));
		btn_müsterikayit.setBounds(112, 271, 89, 45);
		w_müsteri.add(btn_müsterikayit);
		
		JButton btn_müsterigiris = new JButton("Giri\u015F Yap");
		btn_müsterigiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_müsteriad.getText().length()==0 || fld_müsteritelefon.getText().length()==0 || fld_müsterimail.getText().length()==0 || fld_müsterisifre.getText().length()==0) {
				Helper.showMsg("fill");	
				}else {
					try {
						boolean control=müsteri.addMüsteri(fld_müsteriad.getText(),fld_müsteritelefon.getText(),fld_müsterimail.getText(),fld_müsterisifre.getText());
						if (control) {
							Helper.showMsg("Baþarýlý Giriþ");
							BiletGUI bGUI=new BiletGUI(müsteri);
							bGUI.setVisible(true);
							dispose();
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
				
			}
			
		});
		btn_müsterigiris.setBackground(UIManager.getColor("Button.shadow"));
		btn_müsterigiris.setBounds(233, 271, 89, 45);
		w_müsteri.add(btn_müsterigiris);
		
		JPanel w_yönetici = new JPanel();
		w_yönetici.setBackground(new Color(204, 102, 153));
		tabbedPane.addTab("Yönetici Giriþ", null, w_yönetici, null);
		w_yönetici.setLayout(null);
		
		JLabel lbl_yöneticiad = new JLabel("AD-SOYAD:");
		lbl_yöneticiad.setForeground(new Color(255, 255, 255));
		lbl_yöneticiad.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbl_yöneticiad.setBounds(17, 37, 70, 31);
		w_yönetici.add(lbl_yöneticiad);
		
		JLabel lbl_yöneticitelefon = new JLabel("TELEFON:");
		lbl_yöneticitelefon.setForeground(new Color(255, 255, 255));
		lbl_yöneticitelefon.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbl_yöneticitelefon.setBounds(19, 93, 66, 31);
		w_yönetici.add(lbl_yöneticitelefon);
		
		JLabel lbl_yöneticimail = new JLabel("E-MA\u0130L:");
		lbl_yöneticimail.setForeground(new Color(255, 255, 255));
		lbl_yöneticimail.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbl_yöneticimail.setBounds(27, 146, 46, 34);
		w_yönetici.add(lbl_yöneticimail);
		
		JLabel lbl_yöneticisifre = new JLabel("\u015E\u0130FRE:");
		lbl_yöneticisifre.setForeground(new Color(255, 255, 255));
		lbl_yöneticisifre.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbl_yöneticisifre.setBounds(27, 205, 46, 34);
		w_yönetici.add(lbl_yöneticisifre);
		
		fld_yöneticiad = new JTextField();
		fld_yöneticiad.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		fld_yöneticiad.setBounds(97, 34, 235, 34);
		w_yönetici.add(fld_yöneticiad);
		fld_yöneticiad.setColumns(10);
		
		fld_yöneticitelefon = new JTextField();
		fld_yöneticitelefon.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		fld_yöneticitelefon.setBounds(97, 90, 235, 34);
		w_yönetici.add(fld_yöneticitelefon);
		fld_yöneticitelefon.setColumns(10);
		
		fld_yöneticimail = new JTextField();
		fld_yöneticimail.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		fld_yöneticimail.setBounds(97, 143, 235, 34);
		w_yönetici.add(fld_yöneticimail);
		fld_yöneticimail.setColumns(10);
		
		fld_yöneticisifre = new JPasswordField();
		fld_yöneticisifre.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		fld_yöneticisifre.setBounds(97, 205, 235, 34);
		w_yönetici.add(fld_yöneticisifre);
		
		JButton btn_yöneticiLogin = new JButton("Giri\u015F Yap");
		btn_yöneticiLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_yöneticiad.getText().length()==0 || fld_yöneticitelefon.getText().length()==0 || fld_yöneticimail.getText().length()==0 || fld_yöneticisifre.getText().length()==0) {
					Helper.showMsg("fill");		
				}
				else {
					try {
						Connection con= conn.connDb();
						Statement st=con.createStatement();
						ResultSet rs=st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_yöneticiad.getText().equals(rs.getString("A")) && fld_yöneticitelefon.getText().equals(rs.getString("B")) && fld_yöneticimail.getText().equals(rs.getString("C")) && fld_yöneticisifre.getText().equals(rs.getString("D"))) {
								if(rs.getString("type").equals("yönetici")) {
									Yönetici ynetici=new Yönetici();
									ynetici.setId(rs.getInt("id"));
									ynetici.setA(rs.getString("A"));
									ynetici.setB(rs.getString("B"));
									ynetici.setC(rs.getString("C"));
									ynetici.setD(rs.getString("D"));
									ynetici.setType(rs.getString("type"));
									YöneticiGUI yGUI=new YöneticiGUI(ynetici);
									yGUI.setVisible(true);
									dispose();
								}
								if(rs.getString("type").equals("müsteri")) {
									Müsteri müsteri=new Müsteri();
									müsteri.setId(rs.getInt("id"));
									müsteri.setA(rs.getString("A"));
									müsteri.setB(rs.getString("B"));
									müsteri.setC(rs.getString("C"));
									müsteri.setD(rs.getString("D"));
									müsteri.setType(rs.getString("type"));
									BiletGUI bGUI=new BiletGUI(müsteri);
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
		btn_yöneticiLogin.setForeground(new Color(0, 0, 0));
		btn_yöneticiLogin.setBackground(UIManager.getColor("Button.shadow"));
		btn_yöneticiLogin.setBounds(233, 271, 89, 45);
		w_yönetici.add(btn_yöneticiLogin);
	}

}
