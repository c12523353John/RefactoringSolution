import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.text.MaskFormatter;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Menu extends JFrame implements MenuInterface{

	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private int position;
	private String password;
	private Customer customer;
	private CustomerAccount acc = new CustomerAccount();
	JFrame frameMain, innerFrame;
	JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
	JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField, passwordTextField;
	JLabel customerIDLabel, passwordLabel;
	Container content;
	Customer e;
	String 	PPS,firstName,surname,DOB,CustomerID;

	public static void main(String[] args) {
		MenuInterface driver = new Menu();
		driver.menuStart();
	}

	@Override
	public void menuStart()
	{
		/*The menuStart method asks the user if they are a new customer, an existing customer or an admin. It will then start the create customer process
		  if they are a new customer, or will ask them to log in if they are an existing customer or admin.*/

		initFrame();
		MainGui mainGui = new MainGui();
		NewUserGui newUserGui = new NewUserGui();
		Container content = frameMain.getContentPane();
		content.setLayout(new GridLayout(2, 1));
		content.add(mainGui.getUserTypePanel());
		content.add(mainGui.getContinuePanel());

		mainGui.getContinueButton().addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				String user = mainGui.getUserType().getSelection().getActionCommand(  );

				//if user selects NEW CUSTOMER--------------------------------------------------------------------------------------
				if("New Customer".equals(user)) {
					frameMain.dispose();		
					initInnerFrame();
					Container content = innerFrame.getContentPane();
					content.setLayout(new BorderLayout());

					newUserGui.getAddCustomerButton().addActionListener(new ActionListener() {
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

							newUserGui.getAddCustomerButton().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									innerFrame.dispose();
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
									menuStart();
									frameMain.dispose();
								}
							});
						}
					});						
					JButton cancel = new JButton("Cancel");					
					cancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							innerFrame.dispose();
							menuStart();
						}
					});

					newUserGui.getButtonPanel().add(newUserGui.addCustomerButton);
					newUserGui.getButtonPanel().add(cancel);
					content.add(newUserGui.getPanel(), BorderLayout.CENTER);
					content.add(newUserGui.getButtonPanel(), BorderLayout.SOUTH);
					innerFrame.setVisible(true);		
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
								innerFrame.dispose();
								loop = false;
								loop2 = false;
								menuStart();
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
								innerFrame.dispose();
								loop2 = false;
								menuStart();
							}
						}
						else {
							loop2 =false;
							cont = true;
						}
					}

					if(cont) {
						innerFrame.dispose();
						loop = false;
						admin();					    
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
								menuStart();
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
								menuStart();
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
						customer(customer);				    
					}				    
				}
				//-----------------------------------------------------------------------------------------------------------------------
			}

			private void initInnerFrame() {
				innerFrame = new JFrame("Create New Customer");
				innerFrame.setSize(400, 300);
				innerFrame.setLocation(200, 200);
				innerFrame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) { System.exit(0); }
				});
			}
		}); frameMain.setVisible(true);	
	}

	private void initFrame() {
		frameMain = new JFrame();
		frameMain.setSize(400, 300);
		frameMain.setLocation(200, 200);
		frameMain.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});
	}

	@Override
	public void admin()
	{
		dispose();
		initFrame();     
		frameMain.setVisible(true);
		NewAdminGui newAdminGui = new NewAdminGui();
		content = frameMain.getContentPane();
		content.setLayout(new GridLayout(9, 1));
		content.add(newAdminGui.getLabel1());
		content.add(newAdminGui.getAccountPanel());
		content.add(newAdminGui.getBankChargesPanel());
		content.add(newAdminGui.getInterestPanel());
		content.add(newAdminGui.getEditCustomerPanel());
		content.add(newAdminGui.getNavigatePanel());
		content.add(newAdminGui.getSummaryPanel());	
		content.add(newAdminGui.getDeleteCustomerPanel());
		content.add(newAdminGui.getReturnPanel());

		newAdminGui.getBankChargesButton().addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				boolean loop = true;
				boolean found = false;

				if(customerList.isEmpty()) {
					JOptionPane.showMessageDialog(frameMain, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					frameMain.dispose();
					admin();
				} else {
					while(loop) {
						Object customerID = JOptionPane.showInputDialog(frameMain, "Customer ID of Customer You Wish to Apply Charges to:");
						for (Customer aCustomer: customerList){
							if(aCustomer.getCustomerID().equals(customerID)) {
								found = true;
								customer = aCustomer; 
								loop = false;
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
								admin();
							}
						} else {
							frameMain.dispose();
							initFrame();          
							frameMain.setVisible(true);

							JComboBox<String> box = new JComboBox<String>();
							for (int i =0; i < customer.getAccounts().size(); i++) {
								box.addItem(customer.getAccounts().get(i).getNumber());
							}

							box.getSelectedItem();

							JPanel boxPanel = new JPanel();
							boxPanel.add(box);

							JPanel buttonPanel = new JPanel();
							JButton continueButton = new JButton("Apply Charge");
							JButton returnButton = new JButton("Return");
							buttonPanel.add(continueButton);
							buttonPanel.add(returnButton);
							Container content = frameMain.getContentPane();
							content.setLayout(new GridLayout(2, 1));

							content.add(boxPanel);
							content.add(buttonPanel);

							if(customer.getAccounts().isEmpty()) {
								JOptionPane.showMessageDialog(frameMain, "This customer has no accounts! \n The admin must add acounts to this customer."   ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
								frameMain.dispose();
								admin();
							} else {
								for(int i=0; i < customer.getAccounts().size(); i++) {
									if(customer.getAccounts().get(i).getNumber() == box.getSelectedItem() ) {
										acc = customer.getAccounts().get(i);
									}
								}

								continueButton.addActionListener(new ActionListener(  ) {
									public void actionPerformed(ActionEvent ae) {
										String euro = "\u20ac";
										if(acc instanceof CustomerDepositAccount) {
											JOptionPane.showMessageDialog(frameMain, "25" + euro + " deposit account fee aplied."  ,"",  JOptionPane.INFORMATION_MESSAGE);
											acc.setBalance(acc.getBalance()-25);
											JOptionPane.showMessageDialog(frameMain, "New balance = " + acc.getBalance() ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
										}

										if(acc instanceof CustomerCurrentAccount){
											JOptionPane.showMessageDialog(frameMain, "15" + euro + " current account fee aplied."  ,"",  JOptionPane.INFORMATION_MESSAGE);
											acc.setBalance(acc.getBalance()-25);
											JOptionPane.showMessageDialog(frameMain, "New balance = " + acc.getBalance() ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
										}
										frameMain.dispose();				
										admin();				
									}		
								});

								returnButton.addActionListener(new ActionListener(  ) {
									public void actionPerformed(ActionEvent ae) {
										frameMain.dispose();		
										menuStart();				
									}
								});	
							}
						}
					}
				}
			}		
		});

		newAdminGui.getInterestButton().addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				boolean loop = true;
				boolean found = false;

				if(customerList.isEmpty()) {
					JOptionPane.showMessageDialog(frameMain, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					frameMain.dispose();
					admin();
				}
				else {
					while(loop) {
						Object customerID = JOptionPane.showInputDialog(frameMain, "Customer ID of Customer You Wish to Apply Interest to:");

						for (Customer aCustomer: customerList){
							if(aCustomer.getCustomerID().equals(customerID)) {
								found = true;
								customer = aCustomer; 
								loop = false;
							}					    	
						}

						if(!found) {
							int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {
								loop = true;
							} else if(reply == JOptionPane.NO_OPTION) {
								frameMain.dispose();
								loop = false;
								admin();
							}
						} else {
							frameMain.dispose();
							initFrame();       
							frameMain.setVisible(true);

							JComboBox<String> box = new JComboBox<String>();
							for (int i =0; i < customer.getAccounts().size(); i++) {
								box.addItem(customer.getAccounts().get(i).getNumber());
							}

							box.getSelectedItem();

							JPanel boxPanel = new JPanel();

							JLabel label = new JLabel("Select an account to apply interest to:");
							boxPanel.add(label);
							boxPanel.add(box);
							JPanel buttonPanel = new JPanel();
							JButton continueButton = new JButton("Apply Interest");
							JButton returnButton = new JButton("Return");
							buttonPanel.add(continueButton);
							buttonPanel.add(returnButton);
							Container content = frameMain.getContentPane();
							content.setLayout(new GridLayout(2, 1));

							content.add(boxPanel);
							content.add(buttonPanel);

							if(customer.getAccounts().isEmpty()) {
								JOptionPane.showMessageDialog(frameMain, "This customer has no accounts! \n The admin must add acounts to this customer."   ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
								frameMain.dispose();
								admin();
							} else {
								for(int i = 0; i < customer.getAccounts().size(); i++) {
									if(customer.getAccounts().get(i).getNumber() == box.getSelectedItem() ) {
										acc = customer.getAccounts().get(i);
									}
								}
								continueButton.addActionListener(new ActionListener(  ) {
									public void actionPerformed(ActionEvent ae) {
										String euro = "\u20ac";
										double interest = 0;
										boolean loop = true;
										
										while(loop) {
											String interestString = JOptionPane.showInputDialog(frameMain, "Enter interest percentage you wish to apply: \n NOTE: Please enter a numerical value. (with no percentage sign) \n E.g: If you wish to apply 8% interest, enter '8'");//the isNumeric method tests to see if the string entered was numeric. 
											if(isNumeric(interestString)) {
												interest = Double.parseDouble(interestString);
												loop = false;
												acc.setBalance(acc.getBalance() + acc.getBalance() * (interest/100));
												JOptionPane.showMessageDialog(frameMain, interest + "% interest applied. \n new balance = " + acc.getBalance() + euro ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
											} else {
												JOptionPane.showMessageDialog(frameMain, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
											}
										}
										frameMain.dispose();				
										admin();				
									}		
								});

								returnButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent ae) {
										frameMain.dispose();		
										menuStart();				
									}
								});	
							}
						}
					}
				}
			}	
		});

		newAdminGui.getEditCustomerButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				boolean loop = true;
				boolean found = false;
				if(customerList.isEmpty()) {
					JOptionPane.showMessageDialog(frameMain, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					frameMain.dispose();
					admin();
				} else {
					while(loop) {
						Object customerID = JOptionPane.showInputDialog(frameMain, "Enter Customer ID:");

						for (Customer aCustomer: customerList){
							if(aCustomer.getCustomerID().equals(customerID)) {
								found = true;
								customer = aCustomer;
							}					    	
						}
						
						if(!found) {
							int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {
								loop = true;
							} else if(reply == JOptionPane.NO_OPTION) {
								frameMain.dispose();
								loop = false;
								admin();
							}
						} else
							loop = false;
					}

					frameMain.dispose();

					frameMain.dispose();
					frameMain = new JFrame("Administrator Menu");
					frameMain.setSize(400, 300);
					frameMain.setLocation(200, 200);
					frameMain.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) { System.exit(0); }
					});       

					firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
					surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
					pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
					dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
					customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
					passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
					firstNameTextField = new JTextField(20);
					surnameTextField = new JTextField(20);
					pPSTextField = new JTextField(20);
					dOBTextField = new JTextField(20);
					customerIDTextField = new JTextField(20);
					passwordTextField = new JTextField(20);

					JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

					JPanel cancelPanel = new JPanel();

					textPanel.add(firstNameLabel);
					textPanel.add(firstNameTextField);
					textPanel.add(surnameLabel);
					textPanel.add(surnameTextField);
					textPanel.add(pPPSLabel);
					textPanel.add(pPSTextField);
					textPanel.add(dOBLabel);
					textPanel.add(dOBTextField);
					textPanel.add(customerIDLabel);
					textPanel.add(customerIDTextField);
					textPanel.add(passwordLabel);
					textPanel.add(passwordTextField);

					firstNameTextField.setText(customer.getFirstName());
					surnameTextField.setText(customer.getSurname());
					pPSTextField.setText(customer.getPPS());
					dOBTextField.setText(customer.getDOB());
					customerIDTextField.setText(customer.getCustomerID());
					passwordTextField.setText(customer.getPassword());	

					//JLabel label1 = new JLabel("Edit customer details below. The save");

					JButton saveButton = new JButton("Save");
					JButton cancelButton = new JButton("Exit");

					cancelPanel.add(cancelButton, BorderLayout.SOUTH);
					cancelPanel.add(saveButton, BorderLayout.SOUTH);
					Container content = frameMain.getContentPane();
					content.setLayout(new GridLayout(2, 1));
					content.add(textPanel, BorderLayout.NORTH);
					content.add(cancelPanel, BorderLayout.SOUTH);

					frameMain.setContentPane(content);
					frameMain.setSize(340, 350);
					frameMain.setLocation(200, 200);
					frameMain.setVisible(true);
					frameMain.setResizable(false);

					saveButton.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							customer.setFirstName(firstNameTextField.getText());
							customer.setSurname(surnameTextField.getText());
							customer.setPPS(pPSTextField.getText());
							customer.setDOB(dOBTextField.getText());
							customer.setCustomerID(customerIDTextField.getText());
							customer.setPassword(passwordTextField.getText());		
							JOptionPane.showMessageDialog(null, "Changes Saved.");
						}		
					});

					cancelButton.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							frameMain.dispose();
							admin();				
						}		
					});		
				}}
		});

		newAdminGui.getSummaryButton().addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				frameMain.dispose();
				frameMain = new JFrame("Summary of Transactions");
				frameMain.setSize(400, 700);
				frameMain.setLocation(200, 200);
				frameMain.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) { System.exit(0); }
				});          
				frameMain.setVisible(true);

				JLabel label1 = new JLabel("Summary of all transactions: ");

				JPanel returnPanel = new JPanel();
				JButton returnButton = new JButton("Return");
				returnPanel.add(returnButton);

				JPanel textPanel = new JPanel();

				textPanel.setLayout( new BorderLayout() );
				JTextArea textArea = new JTextArea(40, 20);
				textArea.setEditable(false);
				textPanel.add(label1, BorderLayout.NORTH);
				textPanel.add(textArea, BorderLayout.CENTER);
				textPanel.add(returnButton, BorderLayout.SOUTH);

				JScrollPane scrollPane = new JScrollPane(textArea);
				textPanel.add(scrollPane);
				//For each customer, for each account, it displays each transaction.
				for (int a = 0; a < customerList.size(); a++) {
					for (int b = 0; b < customerList.get(a).getAccounts().size(); b ++ ) {
						acc = customerList.get(a).getAccounts().get(b);
						for (int c = 0; c < customerList.get(a).getAccounts().get(b).getTransactionList().size(); c++) {
							textArea.append(acc.getTransactionList().get(c).toString());
							//Int total = acc.getTransactionList().get(c).getAmount(); //I was going to use this to keep a running total but I couldnt get it  working.
						}				
					}				
				}

				textPanel.add(textArea);
				content.removeAll();

				Container content = frameMain.getContentPane();
				content.setLayout(new GridLayout(1, 1));
				content.add(textPanel);

				returnButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						frameMain.dispose();			
						admin();				
					}		
				});	
			}	
		});

		newAdminGui.getNavigateButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frameMain.dispose();

				if(customerList.isEmpty()) {
					JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
					admin();
				} else {

					JButton first, previous, next, last, cancel;
					JPanel gridPanel, buttonPanel, cancelPanel;			

					Container content = getContentPane();

					content.setLayout(new BorderLayout());

					buttonPanel = new JPanel();
					gridPanel = new JPanel(new GridLayout(8, 2));
					cancelPanel = new JPanel();

					firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
					surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
					pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
					dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
					customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
					passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
					firstNameTextField = new JTextField(20);
					surnameTextField = new JTextField(20);
					pPSTextField = new JTextField(20);
					dOBTextField = new JTextField(20);
					customerIDTextField = new JTextField(20);
					passwordTextField = new JTextField(20);

					first = new JButton("First");
					previous = new JButton("Previous");
					next = new JButton("Next");
					last = new JButton("Last");
					cancel = new JButton("Cancel");

					firstNameTextField.setText(customerList.get(0).getFirstName());
					surnameTextField.setText(customerList.get(0).getSurname());
					pPSTextField.setText(customerList.get(0).getPPS());
					dOBTextField.setText(customerList.get(0).getDOB());
					customerIDTextField.setText(customerList.get(0).getCustomerID());
					passwordTextField.setText(customerList.get(0).getPassword());

					firstNameTextField.setEditable(false);
					surnameTextField.setEditable(false);
					pPSTextField.setEditable(false);
					dOBTextField.setEditable(false);
					customerIDTextField.setEditable(false);
					passwordTextField.setEditable(false);

					gridPanel.add(firstNameLabel);
					gridPanel.add(firstNameTextField);
					gridPanel.add(surnameLabel);
					gridPanel.add(surnameTextField);
					gridPanel.add(pPPSLabel);
					gridPanel.add(pPSTextField);
					gridPanel.add(dOBLabel);
					gridPanel.add(dOBTextField);
					gridPanel.add(customerIDLabel);
					gridPanel.add(customerIDTextField);
					gridPanel.add(passwordLabel);
					gridPanel.add(passwordTextField);

					buttonPanel.add(first);
					buttonPanel.add(previous);
					buttonPanel.add(next);
					buttonPanel.add(last);

					cancelPanel.add(cancel);

					content.add(gridPanel, BorderLayout.NORTH);
					content.add(buttonPanel, BorderLayout.CENTER);
					content.add(cancelPanel, BorderLayout.AFTER_LAST_LINE);
					first.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							position = 0;
							firstNameTextField.setText(customerList.get(0).getFirstName());
							surnameTextField.setText(customerList.get(0).getSurname());
							pPSTextField.setText(customerList.get(0).getPPS());
							dOBTextField.setText(customerList.get(0).getDOB());
							customerIDTextField.setText(customerList.get(0).getCustomerID());
							passwordTextField.setText(customerList.get(0).getPassword());				
						}		
					});

					previous.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							if(position < 1){
								//don't do anything
							}
							else {
								position = position - 1;

								firstNameTextField.setText(customerList.get(position).getFirstName());
								surnameTextField.setText(customerList.get(position).getSurname());
								pPSTextField.setText(customerList.get(position).getPPS());
								dOBTextField.setText(customerList.get(position).getDOB());
								customerIDTextField.setText(customerList.get(position).getCustomerID());
								passwordTextField.setText(customerList.get(position).getPassword());
							}			
						}		
					});

					next.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							if(position == customerList.size()-1) {
								//don't do anything
							} else {
								position = position + 1;

								firstNameTextField.setText(customerList.get(position).getFirstName());
								surnameTextField.setText(customerList.get(position).getSurname());
								pPSTextField.setText(customerList.get(position).getPPS());
								dOBTextField.setText(customerList.get(position).getDOB());
								customerIDTextField.setText(customerList.get(position).getCustomerID());
								passwordTextField.setText(customerList.get(position).getPassword());
							}		
						}		
					});

					last.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							position = customerList.size() - 1;
							firstNameTextField.setText(customerList.get(position).getFirstName());
							surnameTextField.setText(customerList.get(position).getSurname());
							pPSTextField.setText(customerList.get(position).getPPS());
							dOBTextField.setText(customerList.get(position).getDOB());
							customerIDTextField.setText(customerList.get(position).getCustomerID());
							passwordTextField.setText(customerList.get(position).getPassword());								
						}		
					});

					cancel.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {				
							dispose();
							admin();
						}		
					});			
					setContentPane(content);
					setSize(400, 300);
					setVisible(true);
				}		
			}
		});

		newAdminGui.getAccountButton().addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				frameMain.dispose();
				if(customerList.isEmpty()) {
					JOptionPane.showMessageDialog(frameMain, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					frameMain.dispose();
					admin();
				} else {
					boolean loop = true;
					boolean found = false;
					while(loop) {
						Object customerID = JOptionPane.showInputDialog(frameMain, "Customer ID of Customer You Wish to Add an Account to:");
						
						for (Customer aCustomer: customerList){
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
								admin();
							}
						} else {
							loop = false;
							//a combo box in an dialog box that asks the admin what type of account they wish to create (deposit/current)
							String[] choices = { "Current Account", "Deposit Account" };
							String account = (String) JOptionPane.showInputDialog(null, "Please choose account type",
									"Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]); 

							if("Current Account".equals(account)) {
								//create current account
								boolean valid = true;
								double balance = 0;
								String number = "C" + (customerList.indexOf(customer)+1) * 10 + (customer.getAccounts().size()+1);//this simple algorithm generates the account number
								ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
								int randomPIN = (int)(Math.random()*9000)+1000;
								String pin = String.valueOf(randomPIN);
								ATMCard atm = new ATMCard(randomPIN, valid);
								CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance, transactionList);
								customer.getAccounts().add(current);
								JOptionPane.showMessageDialog(frameMain, "Account number = " + number +"\n PIN = " + pin  ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);
								frameMain.dispose();
								admin();
							}

							if("Deposit Account".equals(account)) {
								//create deposit account
								double balance = 0, interest = 0;
								String number = "D" + (customerList.indexOf(customer)+1) * 10 + (customer.getAccounts().size()+1);//this simple algorithm generates the account number
								ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
								CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number, balance, transactionList);
								customer.getAccounts().add(deposit);
								JOptionPane.showMessageDialog(frameMain, "Account number = " + number ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);
								frameMain.dispose();
								admin();
							}
						}			   
					}
				}
			}
		});		

		newAdminGui.deleteCustomer.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				boolean found = true, loop = true;
				if(customerList.isEmpty()) {
					JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
					dispose();
					admin();
				} else {
						Object customerID = JOptionPane.showInputDialog(frameMain, "Customer ID of Customer You Wish to Delete:");
						for (Customer aCustomer: customerList){
							if(aCustomer.getCustomerID().equals(customerID)) {
								found = true;
								customer = aCustomer; 
								loop = false;
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
								admin();
							}
						} else if(customer.getAccounts().size()>0) {
							JOptionPane.showMessageDialog(frameMain, "This customer has accounts. \n You must delete a customer's accounts before deleting a customer " ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							customerList.remove(customer);
							JOptionPane.showMessageDialog(frameMain, "Customer Deleted " ,"Success.",  JOptionPane.INFORMATION_MESSAGE);
						}
					}
			}
		});		

		newAdminGui.deleteAccount.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				boolean found = true, loop = true;
					Object customerID = JOptionPane.showInputDialog(frameMain, "Customer ID of Customer from which you wish to delete an account");
					for (Customer aCustomer: customerList){
						if(aCustomer.getCustomerID().equals(customerID)) {
							found = true;
							customer = aCustomer; 
							loop = false;
						}					    	
					}

					if(!found) {
						int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							loop = true;
						} else if(reply == JOptionPane.NO_OPTION) {
							frameMain.dispose();
							loop = false;
							admin();
						}
					}
				}
		});		
		newAdminGui.returnButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				frameMain.dispose();
				menuStart();				
			}
		});		
	}

	@Override
	public void customer(Customer e1) {	
		frameMain = new JFrame("Customer Menu");
		e1 = e;
		frameMain.setSize(400, 300);
		frameMain.setLocation(200, 200);
		frameMain.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});          
		frameMain.setVisible(true);

		if(e.getAccounts().size() == 0) {
			JOptionPane.showMessageDialog(frameMain, "This customer does not have any accounts yet. \n An admin must create an account for this customer \n for them to be able to use customer functionality. " ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
			frameMain.dispose();				
			menuStart();
		} else {
			JPanel buttonPanel = new JPanel();
			JPanel boxPanel = new JPanel();
			JPanel labelPanel = new JPanel();

			JLabel label = new JLabel("Select Account:");
			labelPanel.add(label);

			JButton returnButton = new JButton("Return");
			buttonPanel.add(returnButton);
			JButton continueButton = new JButton("Continue");
			buttonPanel.add(continueButton);

			JComboBox<String> box = new JComboBox<String>();
			for (int i =0; i < e.getAccounts().size(); i++) {
				box.addItem(e.getAccounts().get(i).getNumber());
			}

			for(int i = 0; i<e.getAccounts().size(); i++) {
				if(e.getAccounts().get(i).getNumber() == box.getSelectedItem() ) {
					acc = e.getAccounts().get(i);
				}
			}

			boxPanel.add(box);
			content = frameMain.getContentPane();
			content.setLayout(new GridLayout(3, 1));
			content.add(labelPanel);
			content.add(boxPanel);
			content.add(buttonPanel);

			returnButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					frameMain.dispose();				
					menuStart();				
				}		
			});

			continueButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					frameMain.dispose();
					frameMain = new JFrame("Customer Menu");
					frameMain.setSize(400, 300);
					frameMain.setLocation(200, 200);
					frameMain.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) { System.exit(0); }
					});          
					frameMain.setVisible(true);

					JPanel statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					JButton statementButton = new JButton("Display Bank Statement");
					statementButton.setPreferredSize(new Dimension(250, 20));

					statementPanel.add(statementButton);

					JPanel lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					JButton lodgementButton = new JButton("Lodge money into account");
					lodgementPanel.add(lodgementButton);
					lodgementButton.setPreferredSize(new Dimension(250, 20));

					JPanel withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					JButton withdrawButton = new JButton("Withdraw money from account");
					withdrawalPanel.add(withdrawButton);
					withdrawButton.setPreferredSize(new Dimension(250, 20));

					JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					JButton returnButton = new JButton("Exit Customer Menu");
					returnPanel.add(returnButton);

					JLabel label1 = new JLabel("Please select an option");

					content = frameMain.getContentPane();
					content.setLayout(new GridLayout(5, 1));
					content.add(label1);
					content.add(statementPanel);
					content.add(lodgementPanel);
					content.add(withdrawalPanel);
					content.add(returnPanel);

					statementButton.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							frameMain.dispose();
							frameMain = new JFrame("Customer Menu");
							frameMain.setSize(400, 600);
							frameMain.setLocation(200, 200);
							frameMain.addWindowListener(new WindowAdapter() {
								public void windowClosing(WindowEvent we) { System.exit(0); }
							});          
							frameMain.setVisible(true);
							JLabel label1 = new JLabel("Summary of account transactions: ");

							JPanel returnPanel = new JPanel();
							JButton returnButton = new JButton("Return");
							returnPanel.add(returnButton);

							JPanel textPanel = new JPanel();

							textPanel.setLayout( new BorderLayout() );
							JTextArea textArea = new JTextArea(40, 20);
							textArea.setEditable(false);
							textPanel.add(label1, BorderLayout.NORTH);
							textPanel.add(textArea, BorderLayout.CENTER);
							textPanel.add(returnButton, BorderLayout.SOUTH);

							JScrollPane scrollPane = new JScrollPane(textArea);
							textPanel.add(scrollPane);

							for (int i = 0; i < acc.getTransactionList().size(); i ++) {
								textArea.append(acc.getTransactionList().get(i).toString());
							}

							textPanel.add(textArea);
							content.removeAll();

							Container content = frameMain.getContentPane();
							content.setLayout(new GridLayout(1, 1));
							content.add(textPanel);

							returnButton.addActionListener(new ActionListener(  ) {
								public void actionPerformed(ActionEvent ae) {
									frameMain.dispose();			
									customer(e);				
								}		
							});										
						}	
					});

					lodgementButton.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							boolean loop = true;
							boolean on = true;
							double balance = 0;

							if(acc instanceof CustomerCurrentAccount) {
								int count = 3;
								int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
								loop = true;

								while(loop) {
									if(count == 0) {
										JOptionPane.showMessageDialog(frameMain, "Pin entered incorrectly 3 times. ATM card locked."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);
										((CustomerCurrentAccount) acc).getAtm().setValid(false);
										customer(e); 
										loop = false;
										on = false;
									}

									String Pin = JOptionPane.showInputDialog(frameMain, "Enter 4 digit PIN;");
									int i = Integer.parseInt(Pin);
									
									if(on) {
										if(checkPin == i) {
											loop = false;
											JOptionPane.showMessageDialog(frameMain, "Pin entry successful" ,  "Pin", JOptionPane.INFORMATION_MESSAGE);

										} else {
											count --;
											JOptionPane.showMessageDialog(frameMain, "Incorrect pin. " + count + " attempts remaining."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);					
										}
									}
								}
							}		
							
							if(on) {
								String balanceTest = JOptionPane.showInputDialog(frameMain, "Enter amount you wish to lodge:");//the isNumeric method tests to see if the string entered was numeric. 
								if(isNumeric(balanceTest)) {
									balance = Double.parseDouble(balanceTest);
									loop = false;
								} else
									JOptionPane.showMessageDialog(frameMain, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
								
								String euro = "\u20ac";
								acc.setBalance(acc.getBalance() + balance);
								Date date = new Date();
								String date2 = date.toString();
								String type = "Lodgement";
								double amount = balance;

								AccountTransaction transaction = new AccountTransaction(date2, type, amount);
								acc.getTransactionList().add(transaction);
								JOptionPane.showMessageDialog(frameMain, balance + euro + " added do you account!" ,"Lodgement",  JOptionPane.INFORMATION_MESSAGE);
								JOptionPane.showMessageDialog(frameMain, "New balance = " + acc.getBalance() + euro ,"Lodgement",  JOptionPane.INFORMATION_MESSAGE);
							}
						}	
					});

					withdrawButton.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							boolean loop = true;
							boolean on = true;
							double withdraw = 0;

							if(acc instanceof CustomerCurrentAccount) {
								int count = 3;
								int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
								loop = true;

								while(loop) {
									if(count == 0) {
										JOptionPane.showMessageDialog(frameMain, "Pin entered incorrectly 3 times. ATM card locked."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);
										((CustomerCurrentAccount) acc).getAtm().setValid(false);
										customer(e); 
										loop = false;
										on = false;
									}

									String Pin = JOptionPane.showInputDialog(frameMain, "Enter 4 digit PIN;");
									int i = Integer.parseInt(Pin);

									if(on) {
										if(checkPin == i) {
											loop = false;
											JOptionPane.showMessageDialog(frameMain, "Pin entry successful" ,  "Pin", JOptionPane.INFORMATION_MESSAGE);
										} else {
											count --;
											JOptionPane.showMessageDialog(frameMain, "Incorrect pin. " + count + " attempts remaining."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);		
										}
									}
								}
							}		
							
							if(on) {
								String balanceTest = JOptionPane.showInputDialog(frameMain, "Enter amount you wish to withdraw (max 500):");//the isNumeric method tests to see if the string entered was numeric. 
								if(isNumeric(balanceTest))
								{
									withdraw = Double.parseDouble(balanceTest);
									loop = false;
								} else {
									JOptionPane.showMessageDialog(frameMain, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
								}
								if(withdraw > 500) {
									JOptionPane.showMessageDialog(frameMain, "500 is the maximum you can withdraw at a time." ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
									withdraw = 0;
								}
								if(withdraw > acc.getBalance()) {
									JOptionPane.showMessageDialog(frameMain, "Insufficient funds." ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
									withdraw = 0;					
								}

								String euro = "\u20ac";
								acc.setBalance(acc.getBalance()-withdraw);
								Date date = new Date();
								String date2 = date.toString();

								String type = "Withdraw";
								double amount = withdraw;

								AccountTransaction transaction = new AccountTransaction(date2, type, amount);
								acc.getTransactionList().add(transaction);

								JOptionPane.showMessageDialog(frameMain, withdraw + euro + " withdrawn." ,"Withdraw",  JOptionPane.INFORMATION_MESSAGE);
								JOptionPane.showMessageDialog(frameMain, "New balance = " + acc.getBalance() + euro ,"Withdraw",  JOptionPane.INFORMATION_MESSAGE);
							}
						}	
					});

					returnButton.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							frameMain.dispose();		
							menuStart();				
						}
					});		}		
			});
		}
	}
	  // a method that tests if a string is numeric
	public static boolean isNumeric(String str) {  
		try  {  
			double d = Double.parseDouble(str);  
		}  catch(NumberFormatException nfe) {  
			return false;  
		}  
		return true;  
	}
}