import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NewAdminGui {
	JPanel deleteCustomerPanel,deleteAccountPanel, bankChargesPanel,interestPanel
	,editCustomerPanel,navigatePanel,summaryPanel,accountPanel, returnPanel;
	JLabel label1;
	JButton deleteCustomer, deleteAccount,bankChargesButton, interestButton,
	editCustomerButton,navigateButton,summaryButton,accountButton,returnButton;
	
	public NewAdminGui() {
		initComponents();
	}
	
	private void initComponents() {
		deleteCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		deleteCustomer = new JButton("Delete Customer");	
		deleteCustomer.setPreferredSize(new Dimension(250, 20));
		deleteCustomerPanel.add(deleteCustomer);

		deleteAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		deleteAccount = new JButton("Delete Account");
		deleteAccount.setPreferredSize(new Dimension(250, 20));	
		deleteAccountPanel.add(deleteAccount);

		bankChargesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bankChargesButton = new JButton("Apply Bank Charges");
		bankChargesButton.setPreferredSize(new Dimension(250, 20));	
		bankChargesPanel.add(bankChargesButton);

		interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		interestButton = new JButton("Apply Interest");
		interestPanel.add(interestButton);
		interestButton.setPreferredSize(new Dimension(250, 20));

		editCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editCustomerButton = new JButton("Edit existing Customer");
		editCustomerPanel.add(editCustomerButton);
		editCustomerButton.setPreferredSize(new Dimension(250, 20));

		navigatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navigateButton = new JButton("Navigate Customer Collection");
		navigatePanel.add(navigateButton);
		navigateButton.setPreferredSize(new Dimension(250, 20));

		summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		summaryButton = new JButton("Display Summary Of All Accounts");
		summaryPanel.add(summaryButton);
		summaryButton.setPreferredSize(new Dimension(250, 20));

		accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		accountButton = new JButton("Add an Account to a Customer");
		accountPanel.add(accountButton);
		accountButton.setPreferredSize(new Dimension(250, 20));

		returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		returnButton = new JButton("Exit Admin Menu");
		returnPanel.add(returnButton);

		label1 = new JLabel("Please select an option");
	}

	public JPanel getDeleteCustomerPanel() {
		return deleteCustomerPanel;
	}

	public void setDeleteCustomerPanel(JPanel deleteCustomerPanel) {
		this.deleteCustomerPanel = deleteCustomerPanel;
	}

	public JPanel getDeleteAccountPanel() {
		return deleteAccountPanel;
	}

	public void setDeleteAccountPanel(JPanel deleteAccountPanel) {
		this.deleteAccountPanel = deleteAccountPanel;
	}

	public JPanel getBankChargesPanel() {
		return bankChargesPanel;
	}

	public void setBankChargesPanel(JPanel bankChargesPanel) {
		this.bankChargesPanel = bankChargesPanel;
	}

	public JPanel getInterestPanel() {
		return interestPanel;
	}

	public void setInterestPanel(JPanel interestPanel) {
		this.interestPanel = interestPanel;
	}

	public JPanel getEditCustomerPanel() {
		return editCustomerPanel;
	}

	public void setEditCustomerPanel(JPanel editCustomerPanel) {
		this.editCustomerPanel = editCustomerPanel;
	}

	public JPanel getNavigatePanel() {
		return navigatePanel;
	}

	public void setNavigatePanel(JPanel navigatePanel) {
		this.navigatePanel = navigatePanel;
	}

	public JPanel getSummaryPanel() {
		return summaryPanel;
	}

	public void setSummaryPanel(JPanel summaryPanel) {
		this.summaryPanel = summaryPanel;
	}

	public JPanel getAccountPanel() {
		return accountPanel;
	}

	public void setAccountPanel(JPanel accountPanel) {
		this.accountPanel = accountPanel;
	}

	public JPanel getReturnPanel() {
		return returnPanel;
	}

	public void setReturnPanel(JPanel returnPanel) {
		this.returnPanel = returnPanel;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}

	public JButton getDeleteCustomer() {
		return deleteCustomer;
	}

	public void setDeleteCustomer(JButton deleteCustomer) {
		this.deleteCustomer = deleteCustomer;
	}

	public JButton getDeleteAccount() {
		return deleteAccount;
	}

	public void setDeleteAccount(JButton deleteAccount) {
		this.deleteAccount = deleteAccount;
	}

	public JButton getBankChargesButton() {
		return bankChargesButton;
	}

	public void setBankChargesButton(JButton bankChargesButton) {
		this.bankChargesButton = bankChargesButton;
	}

	public JButton getInterestButton() {
		return interestButton;
	}

	public void setInterestButton(JButton interestButton) {
		this.interestButton = interestButton;
	}

	public JButton getEditCustomerButton() {
		return editCustomerButton;
	}

	public void setEditCustomerButton(JButton editCustomerButton) {
		this.editCustomerButton = editCustomerButton;
	}

	public JButton getNavigateButton() {
		return navigateButton;
	}

	public void setNavigateButton(JButton navigateButton) {
		this.navigateButton = navigateButton;
	}

	public JButton getSummaryButton() {
		return summaryButton;
	}

	public void setSummaryButton(JButton summaryButton) {
		this.summaryButton = summaryButton;
	}

	public JButton getAccountButton() {
		return accountButton;
	}

	public void setAccountButton(JButton accountButton) {
		this.accountButton = accountButton;
	}

	public JButton getReturnButton() {
		return returnButton;
	}

	public void setReturnButton(JButton returnButton) {
		this.returnButton = returnButton;
	}
	
	

}
