import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * This class will create the top panel of the application
 * including the top menu and display of the team's logo
 * 
 * @author CAMESYS
 */
public class TopPanel extends JPanel
{
	/* *-*-*-*-*-*-* Constructor *-*-*-*-*-*-* */
	public TopPanel() {	initGUI();	}
	
	/* *-*-*-*-*-*-* initGUI generates the view *-*-*-*-*-*-* */
	public void initGUI()
	{
    	BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
    	this.setLayout(layout);
    	
    	Menu topMenu = new Menu();
    	this.add(topMenu);

    	File teamLogo = new File("images/TEAMLOGO.PNG");
    	boolean exists = teamLogo.exists();
    	if (exists) {	this.add(new LogoLoader());	}
    	
	}// End initGUI

}	/* *-*-*-* TopPanel.java *-*-*-* */
