import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Gui extends javax.swing.JPanel {
	
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private String password;
	JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
	JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField, passwordTextField;
	JLabel customerIDLabel, passwordLabel;
	Container content;
	Customer e;
	JPanel buttonPanel;
	JButton addCustomerButton;
	String 	PPS,firstName,surname,DOB,CustomerID;
	JFrame frameMain, frame1;
	
	public Gui() {
		initComponents();
	}

	private void initComponents() {
		
		Menu menu = new Menu();
		frameMain = new JFrame("User Type");
		frameMain.setSize(400, 300);
		frameMain.setLocation(200, 200);
		frameMain.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});

		JPanel userTypePanel = new JPanel();
		final ButtonGroup userType = new ButtonGroup();
		JRadioButton radioButton;
		userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
		radioButton.setActionCommand("Customer");
		userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("Administrator"));
		radioButton.setActionCommand("Administrator");
		userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("New Customer"));
		radioButton.setActionCommand("New Customer");
		userType.add(radioButton);

		JPanel continuePanel = new JPanel();
		JButton continueButton = new JButton("Continue");
		continuePanel.add(continueButton);

		Container content = frameMain.getContentPane();
		content.setLayout(new GridLayout(2, 1));
		content.add(userTypePanel);
		content.add(continuePanel);

		continueButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				String user = userType.getSelection().getActionCommand(  );

				//if user selects NEW CUSTOMER--------------------------------------------------------------------------------------
				if("New Customer".equals(user)) {
					frameMain.dispose();		
					frame1 = new JFrame("Create New Customer");
					frame1.setSize(400, 300);
					frame1.setLocation(200, 200);
					frame1.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) { System.exit(0); }
					});
					Container content = frame1.getContentPane();
					content.setLayout(new BorderLayout());

					firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
					surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
					pPPSLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
					dOBLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
					firstNameTextField = new JTextField(20);
					surnameTextField = new JTextField(20);
					pPSTextField = new JTextField(20);
					dOBTextField = new JTextField(20);
					JPanel panel = new JPanel(new GridLayout(6, 2));
					panel.add(firstNameLabel);
					panel.add(firstNameTextField);
					panel.add(surnameLabel);
					panel.add(surnameTextField);
					panel.add(pPPSLabel);
					panel.add(pPSTextField);
					panel.add(dOBLabel);
					panel.add(dOBTextField);

					buttonPanel = new JPanel();
					addCustomerButton = new JButton("Add");

					addCustomerButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							addCustomer();	
						}
						
						private void addCustomer() {
							PPS = pPSTextField.getText();
							firstName = firstNameTextField.getText();
							surname = surnameTextField.getText();
							DOB = dOBTextField.getText();
							password = "";
							CustomerID = "ID"+PPS ;

							addCustomerButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									frame1.dispose();
									boolean loop = true;
									while(loop){
										password = JOptionPane.showInputDialog(frameMain, "Enter 7 character Password;");
										if(password.length() != 7)//Making sure password is 7 characters
											JOptionPane.showMessageDialog(null, null, "Password must be 7 charatcers long", JOptionPane.OK_OPTION);
										else
											loop = false;
									}
									ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount> ();
									Customer customer = new Customer(PPS, surname, firstName, DOB, CustomerID, password, accounts);
									customerList.add(customer);
									JOptionPane.showMessageDialog(frameMain, "CustomerID = " + CustomerID +"\n Password = " + password  ,"Customer created.",  JOptionPane.INFORMATION_MESSAGE);
									menu.menuStart();
									frameMain.dispose();
								}
							});
						}
					});						
					JButton cancel = new JButton("Cancel");					
					cancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame1.dispose();
							menu.menuStart();
						}
					});

					buttonPanel.add(addCustomerButton);
					buttonPanel.add(cancel);
					content.add(panel, BorderLayout.CENTER);
					content.add(buttonPanel, BorderLayout.SOUTH);
					frame1.setVisible(true);		
				}
				
				//------------------------------------------------------------------------------------------------------------------

				//if user select ADMIN----------------------------------------------------------------------------------------------
				if("Administrator".equals(user)	) {
					boolean loop = true, loop2 = true;
					boolean cont = false;
					while(loop) {
						Object adminUsername = JOptionPane.showInputDialog(frameMain, "Enter Administrator Username:");
						//search admin list for admin with matching admin username
							if(!"admin".equals(adminUsername)) {
							int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?", JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {
								loop = true;
							} else if(reply == JOptionPane.NO_OPTION) {
								frame1.dispose();
								loop = false;
								loop2 = false;
								menu.menuStart();
							}
						}
						else
							loop = false;			    
					}

					while(loop2) {
						Object adminPassword = JOptionPane.showInputDialog(frameMain, "Enter Administrator Password;");
						//search admin list for admin with matching admin password
						if(!"admin11".equals(adminPassword)) {
							int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect Password. Try again?", JOptionPane.YES_NO_OPTION);
							if(reply == JOptionPane.NO_OPTION){
								frame1.dispose();
								loop2 = false;
								menu.menuStart();
							}
						}
						else {
							loop2 =false;
							cont = true;
						}
					}

					if(cont) {
						frame1.dispose();
						loop = false;
						menu.admin();					    
					}					    
				}
				//----------------------------------------------------------------------------------------------------------------

				//if user selects CUSTOMER ---------------------------------------------------------------------------------------- 
				if("Customer".equals(user)	) {
					boolean loop = true, loop2 = true;
					boolean cont = false;
					boolean found = false;
					Customer customer = null;
					while(loop) {
						Object customerID = JOptionPane.showInputDialog(frameMain, "Enter Customer ID:");

						for (Customer aCustomer: customerList){
							//search customer list for matching customer ID
							if(aCustomer.getCustomerID().equals(customerID)) {
								found = true;
								customer = aCustomer;
							}					    	
						}
						if(!found) {
							int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {
								loop = true;
							}
							else if(reply == JOptionPane.NO_OPTION) {
								frameMain.dispose();
								loop = false;
								loop2 = false;
								menu.menuStart();
							}
						}
						else
							loop = false;

					}
					while(loop2) {
						Object customerPassword = JOptionPane.showInputDialog(frameMain, "Enter Customer Password;");
						//check if custoemr password is correct
						if(!customer.getPassword().equals(customerPassword)) {
							int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?", JOptionPane.YES_NO_OPTION);
							if(reply == JOptionPane.NO_OPTION){
								frameMain.dispose();
								loop2 = false;
								menu.menuStart();
							}
						}
						else
						{
							loop2 =false;
							cont = true;
						}
					}

					if(cont) {
						frameMain.dispose();
						loop = false;
						menu.customer(customer);				    
					}				    
				}
				//-----------------------------------------------------------------------------------------------------------------------
			}
		});
		
	}

}
