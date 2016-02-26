import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AdminMenuGui {
	JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel, customerIDLabel, passwordLabel;
	JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField, passwordTextField;
	JPanel textPanel, cancelPanel;
	JButton saveButton,cancelButton;
	
	public AdminMenuGui() {
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

		textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		cancelPanel = new JPanel();

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
		
		saveButton = new JButton("Save");
		cancelButton = new JButton("Exit");
	}

	public JLabel getFirstNameLabel() {
		return firstNameLabel;
	}

	public void setFirstNameLabel(JLabel firstNameLabel) {
		this.firstNameLabel = firstNameLabel;
	}

	public JLabel getSurnameLabel() {
		return surnameLabel;
	}

	public void setSurnameLabel(JLabel surnameLabel) {
		this.surnameLabel = surnameLabel;
	}

	public JLabel getpPPSLabel() {
		return pPPSLabel;
	}

	public void setpPPSLabel(JLabel pPPSLabel) {
		this.pPPSLabel = pPPSLabel;
	}

	public JLabel getdOBLabel() {
		return dOBLabel;
	}

	public void setdOBLabel(JLabel dOBLabel) {
		this.dOBLabel = dOBLabel;
	}

	public JLabel getCustomerIDLabel() {
		return customerIDLabel;
	}

	public void setCustomerIDLabel(JLabel customerIDLabel) {
		this.customerIDLabel = customerIDLabel;
	}

	public JLabel getPasswordLabel() {
		return passwordLabel;
	}

	public void setPasswordLabel(JLabel passwordLabel) {
		this.passwordLabel = passwordLabel;
	}

	public JTextField getFirstNameTextField() {
		return firstNameTextField;
	}

	public void setFirstNameTextField(JTextField firstNameTextField) {
		this.firstNameTextField = firstNameTextField;
	}

	public JTextField getSurnameTextField() {
		return surnameTextField;
	}

	public void setSurnameTextField(JTextField surnameTextField) {
		this.surnameTextField = surnameTextField;
	}

	public JTextField getpPSTextField() {
		return pPSTextField;
	}

	public void setpPSTextField(JTextField pPSTextField) {
		this.pPSTextField = pPSTextField;
	}

	public JTextField getdOBTextField() {
		return dOBTextField;
	}

	public void setdOBTextField(JTextField dOBTextField) {
		this.dOBTextField = dOBTextField;
	}

	public JTextField getCustomerIDTextField() {
		return customerIDTextField;
	}

	public void setCustomerIDTextField(JTextField customerIDTextField) {
		this.customerIDTextField = customerIDTextField;
	}

	public JTextField getPasswordTextField() {
		return passwordTextField;
	}

	public void setPasswordTextField(JTextField passwordTextField) {
		this.passwordTextField = passwordTextField;
	}

	public JPanel getTextPanel() {
		return textPanel;
	}

	public void setTextPanel(JPanel textPanel) {
		this.textPanel = textPanel;
	}

	public JPanel getCancelPanel() {
		return cancelPanel;
	}

	public void setCancelPanel(JPanel cancelPanel) {
		this.cancelPanel = cancelPanel;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}
	
	
	
	

}
