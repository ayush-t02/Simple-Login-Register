import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Toolkit;
import java.awt.event.FocusListener;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VAA_GUI extends JFrame {

	private JPanel contentPane;
	private final JLabel loginimg = new JLabel("New label");
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VAA_GUI frame = new VAA_GUI();
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
	Connection con;
	PreparedStatement pst, pst1;
	private JButton registerbtn;
	
	public void Connect(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/vaa-db", "root", "");
		}
		catch(ClassNotFoundException ex){
			
		}
		catch(SQLException ex){
			
		}
	}
	
	public VAA_GUI() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 438);
		setTitle("Ayush Tripathi");
		requestFocusInWindow();
//		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Experiments VIT\\Sem 3\\Java Experiments\\VAA\\src\\vaa-bg.jpg"));
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//		contentPane.requestFocus(true);
		loginimg.setBounds(0, 0, 760, 399);
		loginimg.setIcon(new ImageIcon("D:\\\\Experiments VIT\\\\Sem 3\\\\Java Experiments\\\\VAA\\\\src\\\\vaa-background.jpg"));
		contentPane.add(loginimg);
		
		JLabel human = new JLabel();
		human.setBounds(342, 48, 90, 87);
		human.setIcon(new ImageIcon("D:\\Experiments VIT\\Sem 3\\Java Experiments\\VAA\\src\\login-human.png"));
		loginimg.add(human);
		
		JButton loginbtn = new JButton("Login");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                try {
                	String name = username.getText();
    				String word = password.getText();
    				pst  = con.prepareStatement("select Username, Password from loginfo where Username=? and Password=?"); 
    				pst.setString(1, name);
					pst.setString(2, word);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next() == true) {
						JOptionPane.showMessageDialog(null, "Login Successful!");
					} else {
						JOptionPane.showMessageDialog(null, "Credentials incorrect!");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		loginimg.add(loginbtn);
		loginbtn.setBounds(330, 258, 89, 30);
		
		registerbtn = new JButton("Register");
		registerbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = username.getText();
					String word = password.getText();
			
					pst = con.prepareStatement("insert into loginfo(Username,Password)values(?,?)");
					pst.setString(1, name);
					pst.setString(2, word);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Registration successful!");
					username.setText("");
					password.setText("");
					username.requestFocus();
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		registerbtn.setBounds(331, 306, 89, 30);
		loginimg.add(registerbtn);
						
						username = new JTextField("Username");
						loginimg.add(username);
						username.addFocusListener(new FocusAdapter() {
							@Override
							public void focusGained(FocusEvent e) {
								if(username.getText().equals("Username")) {
									username.setText("");
								}
							}
							@Override
							public void focusLost(FocusEvent e) {
								if(username.getText().equals("")) {
									username.setText("Username");
								}
							}
						});
						username.setBounds(300, 157, 157, 32);
						username.setColumns(10);
						
						password = new JTextField("Password");
						loginimg.add(password);
						password.addFocusListener(new FocusAdapter() {
							@Override
							public void focusGained(FocusEvent e) {
								if(password.getText().equals("Password")) {
									password.setText("");
								}
							}
							@Override
							public void focusLost(FocusEvent e) {
								if(password.getText().equals("")) {
									password.setText("Password");
								}
							}
						});
						password.setColumns(10);
						password.setBounds(300, 204, 157, 32);
	}
}