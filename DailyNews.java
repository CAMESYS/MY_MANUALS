import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class DailyNews
{
	
	/* *-*-*-*-*-*-* Global Attributes *-*-*-*-*-*-* */
	public static JFrame mainScreen;

	/* *-*-*-*-*-*-* Constructor *-*-*-*-*-*-* */
	public DailyNews()	{	}	// Not used at the moment, maybe for testing later
	
	/* *-*-*-*-*-*-* Main Method / Entry Point *-*-*-*-*-*-* */
	public static void main(String[] args) 
	{
		DailyNews mainProgram = new DailyNews();
		mainProgram.initGUI();
	}// End of main
	
	/* *-*-*-*-*-*-* initGUI generates the view/main screen *-*-*-*-*-*-* */
	private void initGUI()
	{
		mainScreen = new JFrame ("SEM - Daily News");
		mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainScreen.getContentPane().setLayout(new BorderLayout());
		mainScreen.setMinimumSize(new Dimension (675,600));			// Width, Height
		mainScreen.setPreferredSize(new Dimension( 900,700));
		
		mainScreen.getContentPane().add(new TopPanel(), BorderLayout.NORTH);
		mainScreen.getContentPane().add(new LeftPanel(), BorderLayout.WEST);
		mainScreen.getContentPane().add(new CenterPanel("initialDN"), BorderLayout.CENTER);
		mainScreen.getContentPane().add(new BottomPanel(), BorderLayout.SOUTH);
		
		mainScreen.pack();
		mainScreen.setLocationRelativeTo(null);
		mainScreen.setVisible(true);
	
	}// End of initGUI

}	/* *-*-*-* End of DailyNews.java *-*-*-* */
