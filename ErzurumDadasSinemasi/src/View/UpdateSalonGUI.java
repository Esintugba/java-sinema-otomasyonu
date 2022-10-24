package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.*;
import Model.Salon;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateSalonGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField fld_düzenle;
	private static Salon salon;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateSalonGUI frame= new UpdateSalonGUI(salon);
					frame.setVisible(true);
					
				} catch (Exception e) {
				}
			
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateSalonGUI(Salon salon) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 259, 186);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Salon Ad\u0131");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(26, 11, 89, 20);
		contentPane.add(lblNewLabel);
		
		fld_düzenle = new JTextField();
		fld_düzenle.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		fld_düzenle.setColumns(10);
		fld_düzenle.setBounds(26, 42, 192, 33);
		fld_düzenle.setText(salon.getName());
		contentPane.add(fld_düzenle);
		
		JButton btn_düzenle = new JButton("D\u00FCzenle");
		btn_düzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						@SuppressWarnings("unused")
						boolean updateSalon = salon.updateSalon(salon.getId(),fld_düzenle.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		btn_düzenle.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btn_düzenle.setBounds(26, 86, 192, 33);
		contentPane.add(btn_düzenle);
	}

}
