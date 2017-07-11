import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/* *-*-*-*-*-*-* Creates the contents of the Center Panel *-*-*-*-*-*-* */

/**
 * This class will display information according to the option
 * selected on the left panel or on the top menu, at start 
 * it will display the latest saved values of the Daily News
 * 
 * This panel is the main display of the program
 * 
 * @author CAMESYS
 */
public class CenterPanel extends JPanel
{
	String toDo;
	
	/* *-*-*-*-*-*-* Constructor *-*-*-*-*-*-* */
	public CenterPanel()
	{
		toDo = "initialDN";
		initGUI();
	}
	
	/* *-*-*-*-*-*-* Parameterized Constructor *-*-*-*-*-*-* */
	public CenterPanel (String action)
	{
		toDo = action;
		initGUI();
	}
	
	/* *-*-*-*-*-*-* initGUI generates the view *-*-*-*-*-*-* */
	public void initGUI()	{
		this.setLayout(new GridLayout(1,1));

		switch (toDo)
		{
			case "initialDN" :
				this.add(new TabTables());
				this.setMinimumSize(new Dimension(500,400));
				this.setPreferredSize(new Dimension (650,500));
				break;
			case "updateDN" :
				this.add(new TabTables());
				break;
			case "buildDN" :
				this.add(new buildDailyNews());
				break;
			default :
				JLabel error =  new JLabel ("Nothing to show at the moment");
				this.add(error);
		}
	}// End of initGUI
	
}	/* *-*-*-* CenterPanel.java *-*-*-* */
