import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NewUserGui {
	JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
	JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField, passwordTextField;
	JPanel buttonPanel;
	JButton addCustomerButton;
	JPanel panel;
	
	public NewUserGui() {
		initComponents();
	}
	
	private void initComponents() {
		firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
		surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
		pPPSLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
		dOBLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
		firstNameTextField = new JTextField(20);
		surnameTextField = new JTextField(20);
		pPSTextField = new JTextField(20);
		dOBTextField = new JTextField(20);
		panel = new JPanel(new GridLayout(6, 2));
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

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public JButton getAddCustomerButton() {
		return addCustomerButton;
	}

	public void setAddCustomerButton(JButton addCustomerButton) {
		this.addCustomerButton = addCustomerButton;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	
	

}
