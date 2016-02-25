import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class BoxPanelGui {
	JPanel boxPanel, buttonPanel;
	JButton continueButton, returnButton;
	JComboBox<String> box;
	
	public BoxPanelGui() {
		initComponents();
	}
	
	private void initComponents() {
		box = new JComboBox<String>();
		boxPanel = new JPanel();
		buttonPanel = new JPanel();
		continueButton = new JButton("Apply Charge");
		returnButton = new JButton("Return");
		buttonPanel.add(continueButton);
		buttonPanel.add(returnButton);
	}

	public JPanel getBoxPanel() {
		return boxPanel;
	}

	public void setBoxPanel(JPanel boxPanel) {
		this.boxPanel = boxPanel;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public JButton getContinueButton() {
		return continueButton;
	}

	public void setContinueButton(JButton continueButton) {
		this.continueButton = continueButton;
	}

	public JButton getReturnButton() {
		return returnButton;
	}

	public void setReturnButton(JButton returnButton) {
		this.returnButton = returnButton;
	}

	public JComboBox<String> getBox() {
		return box;
	}

	public void setBox(JComboBox<String> box) {
		this.box = box;
	}
	
	
	

}
