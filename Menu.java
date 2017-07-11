import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * This class will generate the main menu on top of the screen
 * including the user name and the current date
 * 
 * @author CAMESYS
 */
public class Menu extends JMenuBar implements ActionListener
{	
	/* *-*-*-*-*-*-* Constructor *-*-*-*-*-*-* */
	public Menu()	{	initGUI();	}
	
	/* *-*-*-*-*-*-* GUI Menu Generator *-*-*-*-*-*-* */
	public void initGUI()	{	this.add(mainMenu());	}
	
	/* *-*-*-*-*-*-* Creates the Menu *-*-*-*-*-*-* */
	public JMenuBar mainMenu()
	{
		JMenuBar menuBar = new JMenuBar();
	
		JMenu file = new JMenu("File");			// File menu
		file.setMnemonic(KeyEvent.VK_F);
		
			JMenuItem genEmailMenuItem = new JMenuItem("Generate Email Template");
			genEmailMenuItem.setActionCommand("fileEmailOp");
			genEmailMenuItem.addActionListener(this);
			file.add(genEmailMenuItem);
		
			JMenuItem genWebMenuItem = new JMenuItem("Generate Web Content");
			genWebMenuItem.setActionCommand("fileHtmlOp");
			genWebMenuItem.addActionListener(this);
			file.add(genWebMenuItem);

			JMenuItem saveMenuItem = new JMenuItem("Save");
			saveMenuItem.setActionCommand("fileSaveOp");
			file.add(saveMenuItem);
			saveMenuItem.addActionListener(this);

			JMenuItem exitMenuItem = new JMenuItem("Exit");
			exitMenuItem.setActionCommand("fileExitOp");
			file.add(exitMenuItem);
			exitMenuItem.addActionListener(this);

		JMenu users = new JMenu("Users");		// Users menu
		users.setMnemonic(KeyEvent.VK_U);
		
			JMenuItem addUserMenuItem = new JMenuItem("Add User");
			addUserMenuItem.setActionCommand("userAddOp");
			users.add(addUserMenuItem);
			addUserMenuItem.addActionListener(this);
		
			JMenuItem updateUserMenuItem = new JMenuItem("Update User");
			updateUserMenuItem.setActionCommand("userUpdateOp");
			users.add(updateUserMenuItem);
			updateUserMenuItem.addActionListener(this);
		
			JMenuItem removeUserMenuItem = new JMenuItem("Remove User");
			removeUserMenuItem.setActionCommand("userRemoveOp");
			users.add(removeUserMenuItem);
			removeUserMenuItem.addActionListener(this);

		JMenu tables = new JMenu("Tables");		// Tables menu
		tables.setMnemonic(KeyEvent.VK_T);
		
			JMenuItem addTableMenuItem = new JMenuItem("Add Table");
			addTableMenuItem.setActionCommand("tableAddOp");
			tables.add(addTableMenuItem);
			addTableMenuItem.addActionListener(this);
		
			JMenuItem updateTableMenuItem = new JMenuItem("Update Table");
			updateTableMenuItem.setActionCommand("tableUpdateOp");
			tables.add(updateTableMenuItem);
			updateTableMenuItem.addActionListener(this);
		
			JMenuItem removeTableMenuItem = new JMenuItem("Remove Table");
			removeTableMenuItem.setActionCommand("tableRemoveOp");
			tables.add(removeTableMenuItem);
			removeTableMenuItem.addActionListener(this);
		
		JMenu help = new JMenu("Help");			// Help menu
		help.setMnemonic(KeyEvent.VK_H);
		
			JMenuItem instructionsMenuItem = new JMenuItem("Instructions");
			instructionsMenuItem.setActionCommand("helpInstructionsOp");
			help.add(instructionsMenuItem);
			instructionsMenuItem.addActionListener(this);
		
			JMenuItem aboutMenuItem = new JMenuItem("About");
			aboutMenuItem.setActionCommand("helpAboutOp");
			help.add(aboutMenuItem);
			aboutMenuItem.addActionListener(this);

		menuBar.add(file);
		menuBar.add(users);
		menuBar.add(tables);
		menuBar.add(help);
		
		menuBar.add(Box.createGlue());	//Sets user/date to the right of screen
		JMenu userID = new JMenu("User: " + System.getProperty("user.name") + "   | ");
		menuBar.add(userID);
		JMenu today = new JMenu(todaysDate());
		menuBar.add(today);
		
		return menuBar;	
	}// End of mainMenu

	/* *-*-*-*-*-*-* Options for Menu Items Selection *-*-*-*-*-*-* */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch (arg0.getActionCommand())
		{
			// Names are build with menu name first and menu option second
			// for example fileSaveOP is the option Save from the menu File
			case "fileEmailOp" :
				System.out.println("User Generate Email Template");
				break;
			case "fileHtmlOp" :
				System.out.println("User Generate Web Content");
				break;
			case "fileSaveOp" :
				System.out.println("User saved current job");
				break;
			case "fileExitOp" :
				System.out.println("User close session");
				System.exit(0);
			case "userAddOp" :
				System.out.println("User added a user");
				break;
			case "userUpdateOp" :
				System.out.println("User update a user's information");
				break;
			case "userRemoveOp" :
				System.out.println("User remove a user");
				break;
			case "tableAddOp" :
				System.out.println("User added a table");
				break;
			case "tableUpdateOp" :
				System.out.println("User updated a table");
				break;
			case "tableRemoveOp" :
				System.out.println("User remove a table");
				break;
			case "helpInstructionsOp" :
				System.out.println("User ask for instructions");
				break;
			case "helpAboutOp" :
				System.out.println("Program Develop by Camesys");
				break;
		}
	}// End of actionPerformed

	/* *-*-*-*-*-*-* Return Today's Date *-*-*-*-*-*-* */
	public String todaysDate()
	{
		String today;
		try
		{	
			// This will build the date in the format -> DayOfWeek Month 1-31, Year
			today = String.format("%1$tA %1$tB %1$td, %1$tY", new Date());
			System.out.println (today);
		} catch (Exception ex) {
			System.out.println("Error setting up today's Date....");
			today = "Not Available";
		}
		return today;
	}// End of todaysDate

}	/* *-*-*-* End of Menu.java *-*-*-* */