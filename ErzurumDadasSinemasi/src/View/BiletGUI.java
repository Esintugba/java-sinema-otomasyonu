package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;
import Helper.Item;
import Model.Film;
import Model.Müsteri;
import Model.Salon;
import Model.Seans;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class BiletGUI extends JFrame {

	private JPanel content_ödeme;
	private static Müsteri müsteri=new Müsteri();
	private static Film filmsaat=new Film();
	private static Film film =new Film();
	private static Salon salon=new Salon();
	private JTable table_seans;
	private DefaultTableModel seansModel;
	private Object[] seansData=null;
	private JTable table_s;
	private DefaultTableModel filmModel;
	private Object[] filmData=null;
	private DefaultTableModel salonModel;
	private Object [] salonData=null;
	private Seans seans =new Seans();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BiletGUI frame = new BiletGUI(müsteri);
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
	public BiletGUI(Müsteri Müsteri) throws SQLException {
		filmModel =new DefaultTableModel();
		Object[] colFilm=new Object[2];
		colFilm[0]="ÝD";
		colFilm[1]="AD";
		filmModel.setColumnIdentifiers(colFilm);
		filmData = new Object[2];
		
		
		
		seansModel=new DefaultTableModel();
		Object[] colSeans = new Object [2];
		colSeans[0]="ID";
		colSeans[1]="Seans";
		seansModel.setColumnIdentifiers(colSeans);
		seansData=new Object[2];
		for(int i=0; i<filmsaat.getSeansList(filmsaat.getId()).size(); i++) {
			seansData[0]=filmsaat.getSeansList(filmsaat.getId()).get(i).getId();
			seansData[1]=filmsaat.getSeansList(filmsaat.getId()).get(i).getSeans();
			seansModel.addRow(seansData);
			
		}
		
		
		setResizable(false);
		setTitle("Bilet Alma Ekran\u0131");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 924, 550);
		content_ödeme = new JPanel();
		content_ödeme.setBackground(new Color(204, 102, 153));
		content_ödeme.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(content_ödeme);
		content_ödeme.setLayout(null);
		
		JPanel w_biletpanel = new JPanel();
		w_biletpanel.setBounds(30, 24, 586, 148);
		w_biletpanel.setBackground(Color.WHITE);
		content_ödeme.add(w_biletpanel);
		w_biletpanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 102, 153));
		panel_1.setForeground(Color.WHITE);
		panel_1.setBounds(20, 11, 169, 126);
		w_biletpanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\u00D6DEME");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1.setBounds(50, 11, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		JRadioButton rdbtn_tam = new JRadioButton("TAM:      15 TL");
		rdbtn_tam.setBackground(new Color(204, 102, 153));
		rdbtn_tam.setFont(new Font("Times New Roman", Font.BOLD, 12));
		rdbtn_tam.setBounds(6, 30, 109, 23);
		panel_1.add(rdbtn_tam);
		
		JRadioButton rdbtn_ogrenci = new JRadioButton("\u00D6\u011ERENC\u0130:   12 TL");
		rdbtn_ogrenci.setBackground(new Color(204, 102, 153));
		rdbtn_ogrenci.setFont(new Font("Times New Roman", Font.BOLD, 12));
		rdbtn_ogrenci.setBounds(6, 56, 127, 23);
		panel_1.add(rdbtn_ogrenci);
		
		JComboBox combo_ödemeyöntemi = new JComboBox();
		combo_ödemeyöntemi.addItem("Ödeme Yöntemi");
		combo_ödemeyöntemi.setModel(new DefaultComboBoxModel(new String[] {"Nakit \u00D6deme", "Kredi Kart\u0131 ile \u00D6deme"}));
		combo_ödemeyöntemi.setBounds(16, 86, 127, 22);
		panel_1.add(combo_ödemeyöntemi);
		
		JList list = new JList();
		list.setBounds(316, 55, -43, -18);
		w_biletpanel.add(list);
		
		JComboBox select_salon = new JComboBox();
		select_salon.setFont(new Font("Times New Roman", Font.BOLD, 12));
		for(int i=0; i<salon.getList().size(); i++) {
			select_salon.addItem(new Item(salon.getList().get(i).getId(),salon.getList().get(i).getName()));
		}
		select_salon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(select_salon.getSelectedIndex()!=0) {
					JComboBox s=(JComboBox) e.getSource();
					Item item=(Item) s.getSelectedItem();
					DefaultTableModel clearModel=(DefaultTableModel) table_rezervasyon.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i=0; i<salon.getSalonFilmList(item.getKey()).size(); i++) {
						filmData[0]=salon.getSalonFilmList(item.getKey()).get(i).getId();
						filmData[1]=salon.getSalonFilmList(item.getKey()).get(i).getA();
						filmModel.addRow(filmData);
						
						}
					} catch (SQLException e1) {
	
						e1.printStackTrace();
					}
				}else {
					DefaultTableModel clearModel=(DefaultTableModel) table_rezervasyon.getModel();
					clearModel.setRowCount(0);
				}
					
			}
		});
		select_salon.setBackground(UIManager.getColor("ComboBox.disabledBackground"));
		select_salon.setBounds(470, 48, 106, 22);
		w_biletpanel.add(select_salon);
		
		JComboBox select_time = new JComboBox();
		select_time.setBackground(UIManager.getColor("ComboBox.disabledBackground"));
		select_time.setFont(new Font("Times New Roman", Font.BOLD, 12));
		select_time.setModel(new DefaultComboBoxModel(new String[] {"9:00", "12:00", "15:00", "18:00", "21:00"}));
		select_time.setBounds(345, 48, 106, 22);
		w_biletpanel.add(select_time);
		
		JLabel lbl_salonsec = new JLabel("SALONLAR");
		lbl_salonsec.setForeground(Color.BLACK);
		lbl_salonsec.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lbl_salonsec.setBounds(475, 23, 90, 14);
		w_biletpanel.add(lbl_salonsec);
		
		JLabel lbl_seans = new JLabel("SEANS");
		lbl_seans.setBackground(Color.BLACK);
		lbl_seans.setForeground(Color.BLACK);
		lbl_seans.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lbl_seans.setBounds(356, 23, 57, 14);
		w_biletpanel.add(lbl_seans);
		
		JDateChooser select_seans = new JDateChooser();
		select_seans.setBounds(217, 48, 106, 21);
		w_biletpanel.add(select_seans);
		
		JLabel lblNewLabel = new JLabel("TAR\u0130H");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(217, 23, 61, 14);
		w_biletpanel.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("A");
		lblNewLabel_4.setBounds(60, 203, 46, 14);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		content_ödeme.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("B");
		lblNewLabel_5.setBounds(155, 203, 46, 14);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 20));
		content_ödeme.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("C");
		lblNewLabel_6.setBounds(254, 203, 46, 14);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 20));
		content_ödeme.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("D");
		lblNewLabel_7.setBounds(354, 203, 46, 14);
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 20));
		content_ödeme.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("E");
		lblNewLabel_8.setBounds(455, 203, 46, 14);
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 20));
		content_ödeme.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("F");
		lblNewLabel_9.setBounds(556, 203, 46, 14);
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 20));
		content_ödeme.add(lblNewLabel_9);
		
		JToggleButton tglbtn_A1 = new JToggleButton("1");
		tglbtn_A1.setBounds(30, 228, 60, 30);
		tglbtn_A1.setBackground(UIManager.getColor("ToggleButton.light"));
		tglbtn_A1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		content_ödeme.add(tglbtn_A1);
		
		JToggleButton tglbtn_A2 = new JToggleButton("2");
		tglbtn_A2.setBounds(30, 269, 60, 30);
		tglbtn_A2.setBackground(UIManager.getColor("ToggleButton.light"));
		tglbtn_A2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		content_ödeme.add(tglbtn_A2);
		
		JToggleButton tglbtn_A3 = new JToggleButton("3");
		tglbtn_A3.setBounds(30, 310, 60, 30);
		tglbtn_A3.setBackground(UIManager.getColor("ToggleButton.light"));
		tglbtn_A3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		content_ödeme.add(tglbtn_A3);
		
		JToggleButton tglbtn_A4 = new JToggleButton("4");
		tglbtn_A4.setBounds(30, 351, 60, 30);
		tglbtn_A4.setBackground(UIManager.getColor("ToggleButton.light"));
		tglbtn_A4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		content_ödeme.add(tglbtn_A4);
		
		JToggleButton tglbtn_A5 = new JToggleButton("5");
		tglbtn_A5.setBounds(30, 392, 60, 30);
		tglbtn_A5.setBackground(UIManager.getColor("ToggleButton.light"));
		tglbtn_A5.setFont(new Font("Times New Roman", Font.BOLD, 20));
		content_ödeme.add(tglbtn_A5);
		
		JToggleButton tglbtn_A6 = new JToggleButton("6");
		tglbtn_A6.setBounds(30, 430, 60, 30);
		tglbtn_A6.setBackground(UIManager.getColor("ToggleButton.light"));
		tglbtn_A6.setFont(new Font("Times New Roman", Font.BOLD, 20));
		content_ödeme.add(tglbtn_A6);
		
		JToggleButton tglbtn_B1 = new JToggleButton("1");
		tglbtn_B1.setBounds(130, 228, 60, 30);
		tglbtn_B1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_B1.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_B1);
		
		JToggleButton tglbtn_B2 = new JToggleButton("2");
		tglbtn_B2.setBounds(130, 269, 60, 30);
		tglbtn_B2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_B2.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_B2);
		
		JToggleButton tglbtn_C1 = new JToggleButton("1");
		tglbtn_C1.setBounds(230, 228, 60, 30);
		tglbtn_C1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_C1.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_C1);
		
		JToggleButton tglbtn_D1 = new JToggleButton("1");
		tglbtn_D1.setBounds(330, 228, 60, 30);
		tglbtn_D1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_D1.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_D1);
		
		JToggleButton tglbtn_E1 = new JToggleButton("1");
		tglbtn_E1.setBounds(430, 228, 60, 30);
		tglbtn_E1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_E1.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_E1);
		
		JToggleButton tglbtn_F1 = new JToggleButton("1");
		tglbtn_F1.setBounds(530, 228, 60, 30);
		tglbtn_F1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_F1.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_F1);
		
		JToggleButton tglbtn_C2 = new JToggleButton("2");
		tglbtn_C2.setBounds(230, 269, 60, 30);
		tglbtn_C2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_C2.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_C2);
		
		JToggleButton tglbtn_D2 = new JToggleButton("2");
		tglbtn_D2.setBounds(330, 269, 60, 30);
		tglbtn_D2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_D2.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_D2);
		
		JToggleButton tglbtn_E2 = new JToggleButton("2");
		tglbtn_E2.setBounds(430, 269, 60, 30);
		tglbtn_E2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_E2.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_E2);
		
		JToggleButton tglbtn_F2 = new JToggleButton("2");
		tglbtn_F2.setBounds(530, 269, 60, 30);
		tglbtn_F2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_F2.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_F2);
		
		JToggleButton tglbtn_B3 = new JToggleButton("3");
		tglbtn_B3.setBounds(130, 310, 60, 30);
		tglbtn_B3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_B3.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_B3);
		
		JToggleButton tglbtn_C3 = new JToggleButton("3");
		tglbtn_C3.setBounds(230, 310, 60, 30);
		tglbtn_C3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_C3.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_C3);
		
		JToggleButton tglbtn_D3 = new JToggleButton("3");
		tglbtn_D3.setBounds(330, 310, 60, 30);
		tglbtn_D3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_D3.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_D3);
		
		JToggleButton tglbtn_E3 = new JToggleButton("3");
		tglbtn_E3.setBounds(430, 310, 60, 30);
		tglbtn_E3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_E3.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_E3);
		
		JToggleButton tglbtn_F3 = new JToggleButton("3");
		tglbtn_F3.setBounds(530, 310, 60, 30);
		tglbtn_F3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_F3.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_F3);
		
		JToggleButton tglbtn_B4 = new JToggleButton("4");
		tglbtn_B4.setBounds(130, 351, 60, 30);
		tglbtn_B4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_B4.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_B4);
		
		JToggleButton tglbtn_C4 = new JToggleButton("4");
		tglbtn_C4.setBounds(230, 351, 60, 30);
		tglbtn_C4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_C4.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_C4);
		
		JToggleButton tglbtn_D4 = new JToggleButton("4");
		tglbtn_D4.setBounds(330, 351, 60, 30);
		tglbtn_D4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_D4.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_D4);
		
		JToggleButton tglbtn_E4 = new JToggleButton("4");
		tglbtn_E4.setBounds(430, 351, 60, 30);
		tglbtn_E4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_E4.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_E4);
		
		JToggleButton tglbtn_F4 = new JToggleButton("4");
		tglbtn_F4.setBounds(530, 351, 60, 30);
		tglbtn_F4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_F4.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_F4);
		
		JToggleButton tglbtn_B5 = new JToggleButton("5");
		tglbtn_B5.setBounds(130, 392, 60, 30);
		tglbtn_B5.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_B5.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_B5);
		
		JToggleButton tglbtn_C5 = new JToggleButton("5");
		tglbtn_C5.setBounds(230, 392, 60, 30);
		tglbtn_C5.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_C5.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_C5);
		
		JToggleButton tglbtn_D5 = new JToggleButton("5");
		tglbtn_D5.setBounds(330, 392, 60, 30);
		tglbtn_D5.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_D5.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_D5);
		
		JToggleButton tglbtn_E5 = new JToggleButton("5");
		tglbtn_E5.setBounds(430, 392, 60, 30);
		tglbtn_E5.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_E5.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_E5);
		
		JToggleButton tglbtn_F5 = new JToggleButton("5");
		tglbtn_F5.setBounds(530, 392, 60, 30);
		tglbtn_F5.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_F5.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_F5);
		
		JToggleButton tglbtn_B6 = new JToggleButton("6");
		tglbtn_B6.setBounds(130, 430, 60, 30);
		tglbtn_B6.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_B6.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_B6);
		
		JToggleButton tglbtn_C6 = new JToggleButton("6");
		tglbtn_C6.setBounds(230, 430, 60, 30);
		tglbtn_C6.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_C6.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_C6);
		
		JToggleButton tglbtn_D6 = new JToggleButton("6");
		tglbtn_D6.setBounds(330, 430, 60, 30);
		tglbtn_D6.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_D6.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_D6);
		
		JToggleButton tglbtn_E6 = new JToggleButton("6");
		tglbtn_E6.setBounds(430, 430, 60, 30);
		tglbtn_E6.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_E6.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_E6);
		
		JToggleButton tglbtn_F6 = new JToggleButton("6");
		tglbtn_F6.setBounds(530, 430, 60, 30);
		tglbtn_F6.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tglbtn_F6.setBackground(UIManager.getColor("ToggleButton.light"));
		content_ödeme.add(tglbtn_F6);
		
		JScrollPane scroll_rezervasyon = new JScrollPane();
		scroll_rezervasyon.setBounds(645, 49, 263, 353);
		content_ödeme.add(scroll_rezervasyon);
		
		table_rezervasyon = new JTable(filmModel);
		table_rezervasyon.setBackground(new Color(255, 255, 255));
		table_rezervasyon.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scroll_rezervasyon.setViewportView(table_rezervasyon);
		
		
		JButton btn_biletal = new JButton("Bilet Al");
		btn_biletal.setBounds(737, 422, 89, 38);
		content_ödeme.add(btn_biletal);
		btn_biletal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
				String seans="";
				try {
					seans=sdf.format(select_seans.getDate());
				} catch (Exception e2) {
				}
				if(seans.length()==0) {
					Helper.showMsg("Lütfen tüm alanlarý doldurun!");
				}else {
					String time=" "+select_time.getSelectedItem().toString()+":00";
					String salon=" "+select_salon.getSelectedItem().toString();
					String selectSeans =seans+time+salon;
					try {
						boolean control=filmsaat.addGösterimSaat(filmsaat.getId(),filmsaat.getA(), selectSeans);
						if (control) {
							Helper.showMsg("success");
							UpdateSeansModel(filmsaat);
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
		btn_biletal.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btn_biletal.setBackground(UIManager.getColor("Button.light"));
		
		JLabel lblNewLabel_2 = new JLabel("F\u0130LMLER");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_2.setBackground(new Color(0, 0, 0));
		lblNewLabel_2.setBounds(741, 11, 89, 27);
		content_ödeme.add(lblNewLabel_2);
	}
	
	ButtonGroup group = new ButtonGroup();
	private JTable table_rezervasyon;
	

	public void UpdateSeansModel(Film filmsaat) throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) table_rezervasyon.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i<filmsaat.getSeansList(filmsaat.getId()).size(); i++) {
			seansData[0]=filmsaat.getSeansList(filmsaat.getId()).get(i).getId();
			seansData[1]=filmsaat.getSeansList(filmsaat.getId()).get(i).getSeans();
			seansModel.addRow(seansData);
			
		}

    }
	
}


