package BankAccountApp;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

//import javax.swing.border.EtchedBorder;
//import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class bookstore{

	private JFrame frmBookShop;
	private JTextField textName;
	private JTextField textEdt;
	private JTextField textPrice;
	private JTable table;
	private JTextField textId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bookstore window = new bookstore();
					window.frmBookShop.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	Connection con;
	
	PreparedStatement pst;
	
	ResultSet rs;
	
	public bookstore() {
		initialize();
		Connect();
		table_load();
	}

	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/book","root","");
		}
		catch(ClassNotFoundException ex) {
			
		}
		catch(SQLException ex) {
			
		}
	}
	
	public void table_load() {
		try {
			pst = con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBookShop = new JFrame();
		frmBookShop.setTitle("Book Shop");
		frmBookShop.setBounds(100, 100, 824, 518);
		frmBookShop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBookShop.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		lblNewLabel.setBounds(294, 23, 177, 77);
		frmBookShop.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(29, 95, 346, 219);
		frmBookShop.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(25, 44, 100, 28);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edtion");
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(25, 100, 66, 28);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(25, 166, 66, 28);
		panel.add(lblNewLabel_2);
		
		textName = new JTextField();
		textName.setBounds(135, 51, 176, 21);
		panel.add(textName);
		textName.setColumns(10);
		
		textEdt = new JTextField();
		textEdt.setBounds(135, 107, 176, 21);
		panel.add(textEdt);
		textEdt.setColumns(10);
		
		textPrice = new JTextField();
		textPrice.setBounds(135, 173, 176, 21);
		panel.add(textPrice);
		textPrice.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(396, 94, 392, 255);
		frmBookShop.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
				
				bname = textName.getText();
				edition = textEdt.getText();
				price = textPrice.getText();
				
				try {
					pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
					pst.setString(1,  bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,  "Record Added!");
					table_load();					
					textName.setText("");
					textEdt.setText("");
					textPrice.setText("");
					textName.requestFocus();				
					}
					catch(SQLException e1) {
						
						e1.printStackTrace();
					}
				
			}
		});
		btnNewButton.setBounds(29, 324, 106, 49);
		frmBookShop.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Exit\r\n");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_2.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnNewButton_2.setBounds(157, 329, 96, 44);
		frmBookShop.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textName.setText("");
				textEdt.setText("");
				textPrice.setText("");
				textId.setText("");
				
			}
		});
		btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnNewButton_1.setBounds(279, 326, 96, 44);
		frmBookShop.getContentPane().add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(29, 394, 346, 63);
		frmBookShop.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Book ID");
		lblNewLabel_2_1.setBounds(10, 13, 69, 20);
		lblNewLabel_2_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_2_1);
		
		textId = new JTextField();
		textId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
						String id = textId.getText();
						pst = con.prepareStatement("select name,edition,price from book where id = ?");
						pst.setString(1, id);
						ResultSet rs = pst.executeQuery();
					
						if(rs.next()==true) {
							String names = rs.getString(1);
							String editions = rs.getString(2);
							String prices = rs.getString(3);		
							
							textName.setText(names);
							textEdt.setText(editions);
							textPrice.setText(prices);
						}
						else {
							textName.setText("");
							textEdt.setText("");
							textPrice.setText("");
						}
					}catch(SQLException ex) {
						
					}	
				
				
			}
		});	
		
		textId.setColumns(10);
		textId.setBounds(130, 13, 176, 21);
		panel_1.add(textId);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id;
				
				
				id = textId.getText();
				
				try {
					pst = con.prepareStatement("delete from book where id = ?");
					pst.setString(1, id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,  "Record Delete!");
					table_load();					
					textName.setText("");
					textEdt.setText("");
					textPrice.setText("");
					textId.setText("");
					textName.requestFocus();				
					}
					catch(SQLException e1) {
						
						e1.printStackTrace();
					}
			}
		});
		btnDelete.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnDelete.setBounds(615, 394, 106, 49);
		frmBookShop.getContentPane().add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price,id;
				
				bname = textName.getText();
				edition = textEdt.getText();
				price = textPrice.getText();
				id = textId.getText();
				
				try {
					pst = con.prepareStatement("update book set name = ?, edition = ?, price = ? where id = ?");
					pst.setString(1,  bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,  "Record Updated!");
					table_load();					
					textName.setText("");
					textEdt.setText("");
					textPrice.setText("");
					textName.requestFocus();				
					}
					catch(SQLException e1) {
						
						e1.printStackTrace();
					}
				
			}
				
				
		
		});
		btnUpdate.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnUpdate.setBounds(430, 394, 96, 49);
		frmBookShop.getContentPane().add(btnUpdate);
	}

}
