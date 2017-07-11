import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 * This class will display any messages from the system
 * at the bottom of the screen
 * 
 * @author CAMESYS
 */
public class BottomPanel extends JPanel
{
	String bottomText;
	
	/* *-*-*-*-*-*-* Constructor *-*-*-*-*-*-* */
	public BottomPanel()
	{
		bottomText = "Welcome to the Daily News reporting system";
		initGUI();
	}
	
	/* *-*-*-*-*-*-* Parameterized Constructor *-*-*-*-*-*-* */
	public BottomPanel(String text)
	{
		bottomText = text;
		initGUI();
	}
	
	/* *-*-*-*-*-*-* initGUI generates the view *-*-*-*-*-*-* */
	public void initGUI()
	{
		this.setLayout(new GridLayout (1,1));
		JTextArea text = new JTextArea((" "+bottomText+"\nCopyright(c) 2017 Camesys"),3,20);
		text.setOpaque(true);
		text.setBackground(new Color(238,238,238));
		Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		text.setBorder(border);
		this.add(text);
	}// End of initGUI
		
}	/* *-*-*-* BottomPanel.java *-*-*-* */
