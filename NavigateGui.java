import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NavigateGui {
	
	JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel, customerIDLabel, passwordLabel;
	JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField, passwordTextField;
	JButton first, previous, next, last, cancel;
	JPanel buttonPanel, gridPanel, cancelPanel;
	
	public NavigateGui() {
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

	public JButton getFirst() {
		return first;
	}

	public void setFirst(JButton first) {
		this.first = first;
	}

	public JButton getPrevious() {
		return previous;
	}

	public void setPrevious(JButton previous) {
		this.previous = previous;
	}

	public JButton getNext() {
		return next;
	}

	public void setNext(JButton next) {
		this.next = next;
	}

	public JButton getLast() {
		return last;
	}

	public void setLast(JButton last) {
		this.last = last;
	}

	public JButton getCancel() {
		return cancel;
	}

	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public JPanel getGridPanel() {
		return gridPanel;
	}

	public void setGridPanel(JPanel gridPanel) {
		this.gridPanel = gridPanel;
	}

	public JPanel getCancelPanel() {
		return cancelPanel;
	}

	public void setCancelPanel(JPanel cancelPanel) {
		this.cancelPanel = cancelPanel;
	}
	
	
	
	

}
