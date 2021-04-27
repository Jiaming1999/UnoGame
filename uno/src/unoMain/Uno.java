package unoMain;

import ControllerGUI.settingController;
import GUI.settingGUI;

public class Uno {

	public static void main (String[] args) {
		settingGUI setting = new settingGUI();
		new settingController(setting);
	}

}
