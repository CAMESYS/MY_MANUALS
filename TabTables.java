import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 * This class creates the tab view for the schedules
 * 
 * @author CAMESYS
 */
public class TabTables extends JPanel	//JTabbedPane	//JPanel
{

/* *-*-*-*-*-*-* Constructor *-*-*-*-*-*-* */
	public TabTables()	{	initGUI();	}
	
/* *-*-*-*-*-*-* GUI Tab Generator *-*-*-*-*-*-* */
	public void initGUI()
	{	
		this.setLayout(new GridLayout(1,1));
		this.setOpaque(true);
		this.setBackground(new Color(238,238,238));
		this.add(myTabs());
	}
	
/* *-*-*-*-*-*-* Creates Tabs *-*-*-*-*-*-* */
	public JTabbedPane myTabs()
	{
		UIManager.put("TabbedPane.selected", Color.LIGHT_GRAY);
		UIManager.put("TabbedPane.selectedForeground", Color.BLACK);
		UIManager.put("TabbedPane.selectedBackground", new Color(238,238,238));
	    //UIManager.put("TabbedPane.selectHighlight", java.awt.Color.RED);
	    UIManager.put("TabbedPane.selectHighlight", java.awt.Color.BLUE);
		
		JTabbedPane mainTab = new JTabbedPane();
		JTabbedPane tabProd = new JTabbedPane();
		tabProd.setOpaque(true);
		tabProd.setBackground(new Color(238,238,238));

	    Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
	    tabProd.setBorder(border);
		
		JLabel tabProd1 = new JLabel("These are the Production Daily Runs");
		tabProd1.setOpaque(true);
		tabProd1.setBackground(new Color(238,238,238));	//184,207,229));
		tabProd.addTab("Daily Runs", null, tabProd1);
		
		JLabel tabProd2 = new JLabel("These are the Production Revenue Ledger");
		//JPanel tabProd2 = new JPanel();					//NEW
		//tabProd2.setLayout(new GridLayout(1,1));		//NEW
		tabProd2.setOpaque(true);
		tabProd2.setBackground(new Color(238,238,238));
		tabProd2.add(new TableBuilder2());				//NEW
		tabProd.addTab("Rev.Ledger", null, tabProd2);
		
		JLabel tabProd3 = new JLabel();
		tabProd3.setOpaque(true);
		tabProd3.setBackground(new Color(238,238,238));
		tabProd.addTab("Weekly Runs", null, tabProd3);

		JTabbedPane tabUA = new JTabbedPane();
		tabUA.setOpaque(true);
		tabUA.setBackground(new Color(238,238,238));
		
		JLabel tabUA1 = new JLabel();
		tabUA1.setOpaque(true);
		tabUA1.setBackground(new Color(238,238,238));
		tabUA.addTab("Daily Runs", null, tabUA1);
		
		JLabel tabUA2 = new JLabel();
		tabUA2.setOpaque(true);
		tabUA2.setBackground(new Color(238,238,238));
		tabUA.addTab("Rev.Ledger", null, tabUA2);
		
		JLabel tabUA3 = new JLabel();
		tabUA3.setOpaque(true);
		tabUA3.setBackground(new Color(238,238,238));
		tabUA.addTab("Weekly Runs", null, tabUA3);

		mainTab.addTab("Production", null, tabProd);
		mainTab.addTab("User Acceptance 2017", null, tabUA);

		JLabel tabUA4 = new JLabel();
		tabUA4.setOpaque(true);
		tabUA4.setBackground(new Color(238,238,238));

		mainTab.addTab("User Acceptance 2018", null, tabUA4);

		return mainTab;
	}

	
/* *-*-*-*-*-*-* Constructor *-*-*-*-*-*-* */
	
	
/* *-*-*-*-*-*-* Constructor *-*-*-*-*-*-* */

	
/* *-*-*-*-*-*-* Constructor *-*-*-*-*-*-* */
	
	
}	/* *-*-*-* End of TabTables.java *-*-*-* */
