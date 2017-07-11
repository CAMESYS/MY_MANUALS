import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * This class will generate the buttons on the left side
 * of the screen
 * 
 * @author CAMESYS
 */
public class LeftPanel extends JPanel implements ActionListener
{
	/* *-*-*-*-*-*-* Attributes *-*-*-*-*-*-* */
	JButton leftButton1, leftButton2, leftButton3, leftButton4, leftButton5, leftButton6;
	JLabel infoMsg2, infoMsg22;
	
	/* *-*-*-*-*-*-* Constructor *-*-*-*-*-*-* */
	public LeftPanel() {	initGUI();	}
	
	/* *-*-*-*-*-*-* initGUI generates the view *-*-*-*-*-*-* */
	public void initGUI()
	{
		this.setLayout(new GridLayout(1,1));
		this.add(leftPanel());
	}
	
	/* *-*-*-*-*-*-* Creates the contents of the Left Panel *-*-*-*-*-*-* */
	public JPanel leftPanel()
	{		
		JPanel leftPanel = new JPanel(new GridLayout(9,1));
			leftPanel.setMinimumSize(new Dimension (100,400));
			leftPanel.setPreferredSize(new Dimension (150,500));
			Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
			leftPanel.setBorder(border);
			
		JPanel infoLeftPanel1 = new JPanel (new GridLayout(2,1));
		JLabel infoMsg11 = new JLabel("<html><h1 align='center'>Daily News</h1></html>");
			infoMsg11.setOpaque(true);
			infoMsg11.setBackground(new Color(238,238,238));	//GRAY
		infoLeftPanel1.add(infoMsg11);
			infoMsg22 = new JLabel("<html><h1 align='center'>System</h1></html>");
			infoMsg22.setOpaque(true);
			infoMsg22.setBackground(new Color(238,238,238));
			//infoMsg22.setForeground(Color.RED);
			//infoMsg22.setHorizontalAlignment(JLabel.CENTER);
		infoLeftPanel1.add(infoMsg22);
		leftPanel.add(infoLeftPanel1);
		
		JPanel infoLeftPanel2 = new JPanel (new GridLayout(2,1));
		leftPanel.add(infoLeftPanel2);
			
		JPanel infoLeftPanel = new JPanel (new GridLayout(2,1));
			JLabel infoMsg1 = new JLabel("Displaying: ");
			infoMsg1.setOpaque(true);
			//infoMsg1.setBackground(new Color(238,238,238));	//GRAY
			infoMsg1.setBackground(new Color(204,204,255));		//BLUE
		infoLeftPanel.add(infoMsg1);
			infoMsg2 = new JLabel("<html><h3 align='center'>Active Runs</h3></html>");
			infoMsg2.setOpaque(true);
			//infoMsg2.setBackground(new Color(238,238,238));	//GRAY
			infoMsg2.setBackground(new Color(204,204,255));		//BLUE
			infoMsg2.setForeground(Color.RED);
			infoMsg2.setHorizontalAlignment(JLabel.CENTER);
		infoLeftPanel.add(infoMsg2);
		infoLeftPanel.setBorder(border);
			
		leftPanel.add(infoLeftPanel);

		leftButton1 = new JButton("Active Runs");
			leftButton1.setActionCommand("leftButton1");
			leftButton1.addActionListener(this);
			leftButton1.setEnabled(false);
		leftPanel.add(leftButton1);
		
		leftButton2 = new JButton("Modify Information");
			leftButton2.setActionCommand("leftButton2");
			leftButton2.addActionListener(this);
		leftPanel.add(leftButton2);
		
		leftButton3 = new JButton("Generate Email");
			leftButton3.setActionCommand("leftButton3");
			leftButton3.addActionListener(this);	
		leftPanel.add(leftButton3);
		
		leftButton4 = new JButton("Generate Html");
			leftButton4.setActionCommand("leftButton4");
			leftButton4.addActionListener(this);
		leftPanel.add(leftButton4);
		
		leftButton5 = new JButton("Save");
			leftButton5.setActionCommand("leftButton5");
			leftButton5.addActionListener(this);
		leftPanel.add(leftButton5);
		
		leftButton6 = new JButton("Exit");
			leftButton6.setActionCommand("leftButton6");
			leftButton6.addActionListener(this);
		leftPanel.add(leftButton6);
		
		return leftPanel;
	}// End of leftPanel

	/* *-*-*-*-*-*-* Take actions according to selected buttons *-*-*-*-*-*-* */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch (arg0.getActionCommand())
		{
			case "leftButton1" :
				System.out.println("User Selected Current Daily News");
				DailyNews.mainScreen.getContentPane().add(new CenterPanel("updateDN"),
						BorderLayout.CENTER);
				DailyNews.mainScreen.revalidate();
				enableButtons();
				leftButton1.setEnabled(false);
				leftButton2.requestFocus();
				infoMsg2.setText("<html><h3>Active Runs</h3></html>");
				break;
			case "leftButton2" :
				System.out.println("User wants to modified current runs");
				DailyNews.mainScreen.getContentPane().add(new CenterPanel("buildDN"),
						BorderLayout.CENTER);
				DailyNews.mainScreen.revalidate();
				enableButtons();
				leftButton2.setEnabled(false);
				infoMsg2.setText("<html><h3>Modify Contents</h3></html>");
				break;
			case "leftButton3" :
				System.out.println("User wants to generate Email");
				enableButtons();
				leftButton3.setEnabled(false);
				infoMsg2.setText("<html><h3>Email Html created</h3></html>");
				break;
			case "leftButton4" :
				System.out.println("User wants to generate HTML");
				enableButtons();
				leftButton4.setEnabled(false);
				infoMsg2.setText("<html><h3>Wiki Html created</h3></html>");
				break;
			case "leftButton5" :
				System.out.println("User is saving information");
				enableButtons();
				leftButton5.setEnabled(false);
				infoMsg2.setText("<html><h3>Changes saved</h3></html>");
				break;
			case "leftButton6" :
				System.out.println("User wants to exit");
				System.exit(0);		// This options closes the program
				//break;
		}
	}// End of ActionPerformed
	
	/* *-*-*-*-*-*-* To enable all buttons at once *-*-*-*-*-*-* */
	public void enableButtons()
	{
		leftButton1.setEnabled(true);
		leftButton2.setEnabled(true);
		leftButton3.setEnabled(true);
		leftButton4.setEnabled(true);
		leftButton5.setEnabled(true);
		leftButton1.requestFocus();		//Set focus to Button 1
	}// End of enableButtons

}	/* *-*-*-* LeftPanel.java *-*-*-* */
