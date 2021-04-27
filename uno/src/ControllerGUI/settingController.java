package ControllerGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.reminderGUI;
import GUI.settingGUI;
import GUI.unoGUI;
import unoMain.Game;

public class settingController {
	private settingGUI setting;
	
	public settingController(settingGUI setting) {
		this.setting = setting;
		setUpSetting(this.setting);	
	}

	/**
	 * set up setting panel button function
	 * @param setting
	 */
	public void setUpSetting(settingGUI setting) {
		setting.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputReal = setting.getInsertPlayer().getText();
				String inputAI = setting.getInsertAIPlayer().getText();
				int playerNum = 0;
				try {
					playerNum = Integer.parseInt(inputAI) + Integer.parseInt(inputReal);
					if (validStart(playerNum)) {
						Game game = new Game(Integer.parseInt(inputReal), Integer.parseInt(inputAI),true);
						game.start(game);
						unoGUI uno = new unoGUI(game);
						new unoController(uno,game);
						setting.getFrame().dispose();
					}else {
						new reminderGUI("please enter valid number");
					}
				}
				catch (NumberFormatException event) {
					new reminderGUI("please enter valid number");
				}
			}
		});
	}
	/**
	 * see if number of players are valid
	 * @param num
	 * @return
	 */
	public boolean validStart(int num) {
		return num > 1 && num < 11;
	}
}
