import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class UserPanelGui {
	JPanel userTypePanel, continuePanel;
	JRadioButton radioButton;
	JButton continueButton;
	final ButtonGroup userType = new ButtonGroup();

	public UserPanelGui() {
		initComponents();
	}

	private void initComponents() {
		
		userTypePanel = new JPanel();
		userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
		radioButton.setActionCommand("Customer");
		userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("Administrator"));
		radioButton.setActionCommand("Administrator");
		userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("New Customer"));
		radioButton.setActionCommand("New Customer");
		userType.add(radioButton);

		continuePanel = new JPanel();
		continueButton = new JButton("Continue");
		continuePanel.add(continueButton);

//		Container content = frameMain.getContentPane();
//		content.setLayout(new GridLayout(2, 1));
//		content.add(userTypePanel);
//		content.add(continuePanel);
	}
	
	public JPanel getUserTypePanel() {
		return userTypePanel;
	}
	
	public JPanel getContinuePanel() {
		return continuePanel;
	}

	public JRadioButton getRadioButton() {
		return radioButton;
	}

	public void setRadioButton(JRadioButton radioButton) {
		this.radioButton = radioButton;
	}

	public JButton getContinueButton() {
		return continueButton;
	}

	public void setContinueButton(JButton continueButton) {
		this.continueButton = continueButton;
	}

	public ButtonGroup getUserType() {
		return userType;
	}

	public void setUserTypePanel(JPanel userTypePanel) {
		this.userTypePanel = userTypePanel;
	}

	public void setContinuePanel(JPanel continuePanel) {
		this.continuePanel = continuePanel;
	}
	
	
	
}
