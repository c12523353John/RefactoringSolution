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
//	String 	PPS,firstName,surname,DOB,CustomerID;

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
									Customer customer = new Customer(pPSTextField.getText().toString(), surnameTextField.getText().toString(), 
											firstNameTextField.getText().toString(), dOBTextField.getText().toString(), "ID"+pPSTextField.getText().toString(), password, accounts);
									customerList.add(customer);
									JOptionPane.showMessageDialog(frameMain, "CustomerID = " + "ID"+pPSTextField.getText().toString() +"\n Password = " + password  ,"Customer created.",  JOptionPane.INFORMATION_MESSAGE);
									frameMain.dispose();
									menuStart();
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
							
							BoxPanelGui boxPanelGui = new BoxPanelGui();

							for (int i =0; i < customer.getAccounts().size(); i++) {
								boxPanelGui.getBox().addItem(customer.getAccounts().get(i).getNumber());
							}

							boxPanelGui.getBox().getSelectedItem();
							boxPanelGui.getBoxPanel().add(boxPanelGui.getBox());
							boxPanelGui.getButtonPanel().add(boxPanelGui.getContinueButton());
							boxPanelGui.getBoxPanel().add(boxPanelGui.getReturnButton());
							Container content = frameMain.getContentPane();
							content.setLayout(new GridLayout(2, 1));
							content.add(boxPanelGui.getBoxPanel());
							content.add(boxPanelGui.getButtonPanel());

							if(customer.getAccounts().isEmpty()) {
								JOptionPane.showMessageDialog(frameMain, "This customer has no accounts! \n The admin must add acounts to this customer."   ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
								frameMain.dispose();
								admin();
							} else {
								for(int i=0; i < customer.getAccounts().size(); i++) {
									if(customer.getAccounts().get(i).getNumber() == boxPanelGui.getBox().getSelectedItem() ) {
										acc = customer.getAccounts().get(i);
									}
								}

								boxPanelGui.getContinueButton().addActionListener(new ActionListener(  ) {
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

								boxPanelGui.getReturnButton().addActionListener(new ActionListener(  ) {
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
							
							BoxPanelGui boxPanelGui = new BoxPanelGui();

							for (int i =0; i < customer.getAccounts().size(); i++) {
								boxPanelGui.getBox().addItem(customer.getAccounts().get(i).getNumber());
							}

							boxPanelGui.getBox().getSelectedItem();

							JLabel label = new JLabel("Select an account to apply interest to:");
							boxPanelGui.getBoxPanel().add(label);
							boxPanelGui.getBoxPanel().add(boxPanelGui.getBox());
							boxPanelGui.getContinueButton().setText("Apply Interest");
							boxPanelGui.getButtonPanel().add(boxPanelGui.getContinueButton());
							boxPanelGui.getButtonPanel().add(boxPanelGui.getReturnButton());
							Container content = frameMain.getContentPane();
							content.setLayout(new GridLayout(2, 1));

							content.add(boxPanelGui.getBoxPanel());
							content.add(boxPanelGui.getButtonPanel());

							if(customer.getAccounts().isEmpty()) {
								JOptionPane.showMessageDialog(frameMain, "This customer has no accounts! \n The admin must add acounts to this customer."   ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
								frameMain.dispose();
								admin();
							} else {
								for(int i = 0; i < customer.getAccounts().size(); i++) {
									if(customer.getAccounts().get(i).getNumber() == boxPanelGui.getBox().getSelectedItem() ) {
										acc = customer.getAccounts().get(i);
									}
								}
								boxPanelGui.getContinueButton().addActionListener(new ActionListener(  ) {
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

								boxPanelGui.getReturnButton().addActionListener(new ActionListener() {
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
					initFrame();   
					AdminMenuGui adminMenuGui = new AdminMenuGui();

					adminMenuGui.getTextPanel().add(adminMenuGui.getFirstNameLabel());;
					adminMenuGui.getTextPanel().add(adminMenuGui.getFirstNameTextField());
					adminMenuGui.getTextPanel().add(adminMenuGui.getSurnameLabel());
					adminMenuGui.getTextPanel().add(adminMenuGui.getSurnameTextField());
					adminMenuGui.getTextPanel().add(adminMenuGui.getpPPSLabel());
					adminMenuGui.getTextPanel().add(adminMenuGui.getPasswordTextField());
					adminMenuGui.getTextPanel().add(adminMenuGui.getdOBLabel());
					adminMenuGui.getTextPanel().add(adminMenuGui.getdOBTextField());
					adminMenuGui.getTextPanel().add(adminMenuGui.getCustomerIDLabel());
					adminMenuGui.getTextPanel().add(adminMenuGui.getCustomerIDTextField());
					adminMenuGui.getTextPanel().add(adminMenuGui.getPasswordLabel());
					adminMenuGui.getTextPanel().add(adminMenuGui.getPasswordTextField());
					
					adminMenuGui.getFirstNameTextField().setText(customer.getFirstName());
					adminMenuGui.getSurnameTextField().setText(customer.getSurname());
					adminMenuGui.getpPSTextField().setText(customer.getPPS());
					adminMenuGui.getdOBTextField().setText(customer.getDOB());
					adminMenuGui.getCustomerIDTextField().setText(customer.getCustomerID());
					adminMenuGui.getPasswordTextField().setText(customer.getPassword());	


					adminMenuGui.getCancelPanel().add(adminMenuGui.getCancelButton(), BorderLayout.SOUTH);
					adminMenuGui.getCancelPanel().add(adminMenuGui.getSaveButton(), BorderLayout.SOUTH);
					Container content = frameMain.getContentPane();
					content.setLayout(new GridLayout(2, 1));
					content.add(adminMenuGui.getTextPanel(), BorderLayout.NORTH);
					content.add(adminMenuGui.getCancelPanel(), BorderLayout.SOUTH);

					initFrame();

					adminMenuGui.getSaveButton().addActionListener(new ActionListener(  ) {
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

					adminMenuGui.getCancelButton().addActionListener(new ActionListener(  ) {
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
				initFrame();
				frameMain.setTitle("Summary of Transactions");
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

					Container content = getContentPane();
					content.setLayout(new BorderLayout());

					NavigateGui navigateGui = new NavigateGui();
					setNavText(navigateGui);

					navigateGui.getFirstNameTextField().setEditable(false);
					navigateGui.getSurnameTextField().setEditable(false);
					navigateGui.getpPSTextField().setEditable(false);
					navigateGui.getdOBTextField().setEditable(false);
					navigateGui.getCustomerIDTextField().setEditable(false);
					navigateGui.getPasswordTextField().setEditable(false);

					navigateGui.getGridPanel().add(navigateGui.getFirstNameLabel());
					navigateGui.getGridPanel().add(firstNameTextField);
					navigateGui.getGridPanel().add(surnameLabel);
					navigateGui.getGridPanel().add(surnameTextField);
					navigateGui.getGridPanel().add(pPPSLabel);
					navigateGui.getGridPanel().add(pPSTextField);
					navigateGui.getGridPanel().add(dOBLabel);
					navigateGui.getGridPanel().add(dOBTextField);
					navigateGui.getGridPanel().add(customerIDLabel);
					navigateGui.getGridPanel().add(customerIDTextField);
					navigateGui.getGridPanel().add(passwordLabel);
					navigateGui.getGridPanel().add(passwordTextField);

					navigateGui.getButtonPanel().add(navigateGui.getFirst());
					navigateGui.getButtonPanel().add(navigateGui.getPrevious());
					navigateGui.getButtonPanel().add(navigateGui.getNext());
					navigateGui.getButtonPanel().add(navigateGui.getLast());

					navigateGui.getCancelPanel().add(navigateGui.getCancel());

					content.add(navigateGui.getGridPanel(), BorderLayout.NORTH);
					content.add(navigateGui.getButtonPanel(), BorderLayout.CENTER);
					content.add(navigateGui.getCancelPanel(), BorderLayout.AFTER_LAST_LINE);
					navigateGui.getFirst().addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							position = 0;
							setNavText(navigateGui);				
						}		
					});

					navigateGui.getPrevious().addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							if(position < 1){
								//don't do anything
							}
							else {
								position = position - 1;

								setNavText(navigateGui);
							}			
						}		
					});

					navigateGui.getNext().addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							if(position == customerList.size()-1) {
								//don't do anything
							} else {
								position = position + 1;
								setNavText(navigateGui);
							}		
						}		
					});

					navigateGui.getLast().addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							position = customerList.size() - 1;
							setNavText(navigateGui);								
						}		
					});

					navigateGui.getCancel().addActionListener(new ActionListener(  ) {
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

			private void setNavText(NavigateGui navigateGui) {
				navigateGui.getFirstNameTextField().setText(customerList.get(0).getFirstName());
				navigateGui.getSurnameTextField().setText(customerList.get(0).getSurname());
				navigateGui.getpPSTextField().setText(customerList.get(0).getPPS());
				navigateGui.getdOBTextField().setText(customerList.get(0).getDOB());
				navigateGui.getCustomerIDTextField().setText(customerList.get(0).getCustomerID());
				navigateGui.getPasswordTextField().setText(customerList.get(0).getPassword());
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