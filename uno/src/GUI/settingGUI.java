package GUI;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This is the panel where player need to insert player number before the game.
 * @author 11043
 *
 */
public class settingGUI {
	JFrame setting = new JFrame();
	JPanel settingPanel = new JPanel();	
	JPanel insertPanel = new JPanel();	
	JTextField insertPlayer = new JTextField("3");		
	JTextField insertAIPlayer = new JTextField("0");
	JLabel realPlayerNum = new JLabel("real players:");
	JLabel aiPlayerNum = new JLabel("NPC players:");
	JButton startGame = new JButton("Start");	
	
	public settingGUI() {
		insertPanelSetUp();
		settingPanelSetUp();	
	}

	public void insertPanelSetUp() {
		insertPanel.setLayout(new GridLayout(0,1));
		insertPanel.add(insertPlayer);
		insertPanel.add(realPlayerNum);
		insertPanel.add(insertAIPlayer);
		insertPanel.add(aiPlayerNum);
		insertPanel.add(startGame);
	}

	public void settingPanelSetUp() {
		settingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		settingPanel.add(insertPanel);
		setting.setSize(400,300);
		setting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setting.setLocationRelativeTo(null);
		setting.setVisible(true);
		setting.add(settingPanel);
		setting.setTitle("Setting");
	}
	
	/**
	 * getter for private variables
	 * @return
	 */
	public JFrame getFrame() {
		return this.setting;
	}
	
	public JPanel getSettingPanel() {
		return this.settingPanel;
	}
	
	public JPanel getInsertPanel() {
		return this.insertPanel;
	}
	
	public JTextField getInsertPlayer() {
		return this.insertPlayer;
	}
	
	public JTextField getInsertAIPlayer() {
		return this.insertAIPlayer;
	}
	
	public JButton getButton() {
		return this.startGame;
	}

}
