package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.*;
import Model.Yönetici;


import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import Helper.*;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class YöneticiGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Yönetici yönetici=new Yönetici();
	Salon salon=new Salon();
	
	private JPanel w_pane;
	private JTextField fld_filmad;
	private JTextField fld_filmdil;
	private JTextField fld_filmtür;
	private JTextField fld_filmid;
	private JTable table_filmler;
	private DefaultTableModel filmModel=null;
	private Object[] filmData=null;
	private JTextField fld_yayinyili;
	private DefaultTableModel salonModel=null;
	private Object[] salonData=null;
	private JTable table_salon;
	private JPopupMenu salonMenu;
	private JTable table_movie;
	private JTextField fld_movie;
	private JTextField fld_salonad;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YöneticiGUI frame = new YöneticiGUI(yönetici);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public YöneticiGUI(Yönetici yönetici) throws SQLException {
		
		filmModel =new DefaultTableModel();
		Object[] colFilmName=new Object[5];
		colFilmName[0]="ÝD";
		colFilmName[1]="AD";
		colFilmName[2]="DÝL";
		colFilmName[3]="TÜR";
		colFilmName[4]="YAYIN YILI";
		filmModel.setColumnIdentifiers(colFilmName);
		filmData = new Object[5];
		
		for (int i=0; i<yönetici.getFilmList().size(); i++) {
			filmData[0]=yönetici.getFilmList().get(i).getId();
			filmData[1]=yönetici.getFilmList().get(i).getA();
			filmData[2]=yönetici.getFilmList().get(i).getB();
			filmData[3]=yönetici.getFilmList().get(i).getC();
			filmData[4]=yönetici.getFilmList().get(i).getD();
			filmModel.addRow(filmData);
		}
		
		salonModel =new DefaultTableModel();
		Object[] colSalon=new Object[2];
		colSalon[0]="ÝD";
		colSalon[1]="Salon Adý";
		salonModel.setColumnIdentifiers(colSalon);
		salonData = new Object[2];
		
		for (int i=0; i<salon.getList().size(); i++) {
			salonData[0]=salon.getList().get(i).getId();
			salonData[1]=salon.getList().get(i).getName();
			salonModel.addRow(salonData);
		}
		
		setTitle("Sinema Y\u00F6netim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 548);
		w_pane = new JPanel();
		w_pane.setBackground(Color.BLACK);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoþgeldiniz,Sayýn "+yönetici.getA());
		lblNewLabel.setBounds(38, 22, 257, 23);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.setBounds(629, 22, 89, 23);
		btnNewButton.setBackground(UIManager.getColor("Button.shadow"));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(38, 56, 717, 440);
		w_tab.setBackground(new Color(255, 0, 0));
		w_pane.add(w_tab);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Filmler", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("F\u0130LM\u0130N ADI:");
		lblNewLabel_1.setBounds(503, 19, 86, 14);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		panel.add(lblNewLabel_1);
		
		fld_filmad = new JTextField();
		fld_filmad.setBounds(503, 44, 168, 25);
		panel.add(fld_filmad);
		fld_filmad.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("D\u0130L\u0130:");
		lblNewLabel_2.setBounds(503, 80, 68, 14);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		panel.add(lblNewLabel_2);
		
		fld_filmdil = new JTextField();
		fld_filmdil.setBounds(503, 105, 168, 25);
		panel.add(fld_filmdil);
		fld_filmdil.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("F\u0130LM T\u00DCR\u00DC:");
		lblNewLabel_3.setBounds(503, 141, 68, 14);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		panel.add(lblNewLabel_3);
		
		fld_filmtür = new JTextField();
		fld_filmtür.setBounds(503, 166, 168, 25);
		panel.add(fld_filmtür);
		fld_filmtür.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("YAYIN YILI:");
		lblNewLabel_4.setBounds(503, 202, 98, 14);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		panel.add(lblNewLabel_4);
		
		JButton btn_addFilm = new JButton("Ekle");
		btn_addFilm.setBounds(503, 273, 168, 23);
		btn_addFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_filmad.getText().length()==0 || fld_filmdil.getText().length()==0 || fld_filmtür.getText().length()==0||fld_yayinyili.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					try {
						boolean control=yönetici.addFilm(fld_filmad.getText(),fld_filmdil.getText(),fld_filmtür.getText(),fld_yayinyili.getText());
						if (control) {
							Helper.showMsg("success");
							fld_filmad.setText(null);
							fld_filmdil.setText(null);
							fld_filmtür.setText(null);
							fld_yayinyili.setText(null);
							UpdateFilmModel();	
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addFilm.setFont(new Font("Times New Roman", Font.BOLD, 12));
		panel.add(btn_addFilm);
		
		JLabel lblNewLabel_5 = new JLabel("F\u0130LM \u0130D:");
		lblNewLabel_5.setBounds(503, 318, 109, 14);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 12));
		panel.add(lblNewLabel_5);
		
		fld_filmid = new JTextField();
		fld_filmid.setBounds(503, 343, 168, 25);
		panel.add(fld_filmid);
		fld_filmid.setColumns(10);
		
		JButton btn_delFilm = new JButton("Sil");
		btn_delFilm.setBounds(503, 378, 168, 23);
		btn_delFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_filmid.getText().length()==0) {
					Helper.showMsg("Lütfen Geçerli Bir Film Seçiniz!");
				}
				else {
					if (Helper.confirm("sure")) {
						int selectID=Integer.parseInt(fld_filmid.getText());
						try {
							boolean control =yönetici.deleteFilm(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_filmid.setText(null);
								UpdateFilmModel();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_delFilm.setFont(new Font("Times New Roman", Font.BOLD, 12));
		panel.add(btn_delFilm);
		
		JScrollPane w_scrollYönetici = new JScrollPane();
		w_scrollYönetici.setBounds(26, 19, 455, 367);
		panel.add(w_scrollYönetici);
		
		table_filmler = new JTable(filmModel);
		w_scrollYönetici.setViewportView(table_filmler);
		table_filmler.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
			try {
				fld_filmid.setText(table_filmler.getValueAt(table_filmler.getSelectedRow(),0).toString());
			}catch (Exception ex) {
				
			}
			}
			
		});
		table_filmler.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType()== TableModelEvent.UPDATE) {
					int selectID=Integer.parseInt(table_filmler.getValueAt(table_filmler.getSelectedRow(),0).toString());
					String selectA=table_filmler.getValueAt(table_filmler.getSelectedRow(),1).toString();
					String selectB=table_filmler.getValueAt(table_filmler.getSelectedRow(),2).toString();
					String selectC=table_filmler.getValueAt(table_filmler.getSelectedRow(),3).toString();
					String selectD=table_filmler.getValueAt(table_filmler.getSelectedRow(),4).toString();
					
					try {
						boolean control =yönetici.updateFilm(selectID, selectA, selectB, selectC, selectD);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		fld_yayinyili = new JTextField();
		fld_yayinyili.setBounds(503, 227, 168, 25);
		panel.add(fld_yayinyili);
		fld_yayinyili.setColumns(10);
		
		JPanel w_salon = new JPanel();
		w_salon.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Salonlar", null, w_salon, null);
		w_salon.setLayout(null);
		
		JScrollPane w_scrollsalon = new JScrollPane();
		w_scrollsalon.setBounds(10, 11, 245, 390);
		w_salon.add(w_scrollsalon);
		
		salonMenu=new JPopupMenu();
		JMenuItem updateMenu=new JMenuItem("Güncelle");
		JMenuItem deleteMenu=new JMenuItem("Sil");
		salonMenu.add(updateMenu);
		salonMenu.add(deleteMenu);
		
	
		
		// Güncellemek istediðimiz veya silmek istediðimiz deðerin üzerine geldiðinde belli olsun istiyoruz;
		updateMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selID=Integer.parseInt(table_salon.getValueAt(table_salon.getSelectedRow(),0).toString());
				Salon selectSalon=salon.getFetch(selID);
				UpdateSalonGUI updateGUI =new UpdateSalonGUI(selectSalon);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
				
				@Override
				public void windowClosed(WindowEvent e) {
						try {
							UpdateSalonModel();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
				}
			});
		}
	});

		table_salon = new JTable(salonModel);
		w_scrollsalon.setViewportView(table_salon);
		table_salon.setComponentPopupMenu(salonMenu);
		table_salon.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
			Point point=e.getPoint();
			int selectedRow =table_salon.rowAtPoint(point);
			table_salon.setRowSelectionInterval(selectedRow, selectedRow);
		}
		
	
		});
		
		
		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					int selID=Integer.parseInt(table_salon.getValueAt(table_salon.getSelectedRow(),0).toString());
					try {
						if (salon.deleteSalon(selID)) {
							Helper.showMsg("success");
							UpdateSalonModel();
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
		

		JLabel lbl_salonad = new JLabel("SALON ADI:");
		lbl_salonad.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbl_salonad.setBounds(265, 29, 86, 14);
		w_salon.add(lbl_salonad);
		
		JButton btn_addSalon = new JButton("Ekle");
		btn_addSalon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_salonad.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					try {
						if(salon.addSalon(fld_salonad.getText())) {
							Helper.showMsg("success");
							fld_salonad.setText(null);
							UpdateSalonModel(); 
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addSalon.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btn_addSalon.setBounds(292, 95, 128, 31);
		w_salon.add(btn_addSalon);
		
		JScrollPane w_scrollfilm = new JScrollPane();
		w_scrollfilm.setBounds(457, 11, 245, 390);
		w_salon.add(w_scrollfilm);
		
		table_movie = new JTable();
		w_scrollfilm.setViewportView(table_movie);
		
		JComboBox select_film = new JComboBox();
		select_film.setBounds(265, 328, 184, 31);
		for(int i=0; i<yönetici.getFilmList().size(); i++) {
			select_film.addItem(new Item(yönetici.getFilmList().get(i).getId(), yönetici.getFilmList().get(i).getA()));
		}
		select_film.addActionListener(e ->  {
			JComboBox s =( JComboBox) e.getSource();
			Item item=(Item) s.getSelectedItem();
			System.out.println(item.getKey() + ":"+item.getValue());
		});
		w_salon.add(select_film);
		
		
		JButton btn_addMovie = new JButton("Ekle");
		btn_addMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_salon.getSelectedRow();
				if(selRow >= 0) {
					String selSalon =table_salon.getModel().getValueAt(selRow, 0).toString();
					int selSalonID=Integer.parseInt(selSalon);
					Item filmItem=(Item) select_film.getSelectedItem();
					try {
						boolean control=yönetici.addMovie(filmItem.getKey(), selSalonID);
						if(control) {
							Helper.showMsg("success");
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					Helper.showMsg("Lütfen Bir Salon Seçiniz!");
				}
			}
		});
		btn_addMovie.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btn_addMovie.setBounds(292, 370, 128, 31);
		w_salon.add(btn_addMovie);
		
		fld_movie = new JTextField();
		fld_movie.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		fld_movie.setColumns(10);
		fld_movie.setBounds(265, 182, 184, 31);
		w_salon.add(fld_movie);
		
		JButton btn_movieSelect = new JButton("Seç");
		btn_movieSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_salon.getSelectedRow();
				if(selRow >= 0) {
					String selSalon=table_salon.getModel().getValueAt(selRow, 0).toString();
					int selSalonID=Integer.parseInt(selSalon);
					DefaultTableModel clearModel=(DefaultTableModel) table_movie.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i=0; i<yönetici.getSalonFilmList(selSalonID).size(); i++) {
							filmData[0]=yönetici.getSalonFilmList(selSalonID).get(i).getId();
							filmData[1]=yönetici.getSalonFilmList(selSalonID).get(i).getA();
							
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table_movie.setModel(filmModel);
				}else {
					Helper.showMsg("Lütfen bir salon seçiniz!");
				}
			}
		});
		btn_movieSelect.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btn_movieSelect.setBounds(292, 241, 128, 31);
		w_salon.add(btn_movieSelect);
		
		fld_salonad = new JTextField();
		fld_salonad.setBounds(265, 54, 182, 30);
		w_salon.add(fld_salonad);
		fld_salonad.setColumns(10);
	}
	
	public void UpdateFilmModel() throws SQLException{
		DefaultTableModel clearModel=(DefaultTableModel) table_filmler.getModel();
		clearModel.setRowCount(0);
		for (int i=0; i<yönetici.getFilmList().size(); i++) {
			filmData[0]=yönetici.getFilmList().get(i).getId();
			filmData[1]=yönetici.getFilmList().get(i).getA();
			filmData[2]=yönetici.getFilmList().get(i).getB();
			filmData[3]=yönetici.getFilmList().get(i).getC();
			filmData[4]=yönetici.getFilmList().get(i).getD();
			filmModel.addRow(filmData);
		
	    }
		
		DefaultTableModel movieModel=new DefaultTableModel();
		Object[] colMovie=new Object[2];
		colMovie[0]="ID";
		colMovie[0]="FÝLM";
		movieModel.setColumnIdentifiers(colMovie);
		Object[] movieData =new Object[2];
	}
	public void UpdateSalonModel() throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) table_salon.getModel();
		clearModel.setRowCount(0);
		for (int i=0; i<salon.getList().size(); i++) {
			salonData[0]=salon.getList().get(i).getId();
			salonData[1]=salon.getList().get(i).getName();
			salonModel.addRow(salonData);
		}
	}
}

