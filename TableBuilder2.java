import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

public class TableBuilder2 extends JPanel
{
	JFrame mainFrame;
	ArrayList<String> schedName = new ArrayList<>();
	ArrayList<String> schedStatus = new ArrayList<>();
	ArrayList<String> schedMsg = new ArrayList<>();
	ArrayList<String> schedAbends = new ArrayList<>();
	JLabel abendsInfo;
	String abends = "NONE";
	
	public TableBuilder2()	{}// 	initGUI();	}		//COMMENT OUT
	
	public void initGUI()	{	
	//	this.setLayout(new GridLayout(1,1));
	//	this.add(table());							//COMMENT OUT
	//{
		
		mainFrame = new JFrame ("SEM - Daily News");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout());
		mainFrame.setPreferredSize(new Dimension( 600,400));	// Width, Height
		mainFrame.setMinimumSize(new Dimension (550,380));
	
		//mainFrame.getContentPane().add(topTable(), BorderLayout.NORTH);
		mainFrame.getContentPane().add(table(), BorderLayout.CENTER);
//		mainFrame.getContentPane().add(leftPanel(), BorderLayout.WEST);		
//		mainFrame.getContentPane().add(rightPanel(), BorderLayout.EAST);
		//mainFrame.getContentPane().add(botTable(), BorderLayout.SOUTH);

		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);		
	}
	
/* *-*-*-*-*-*-* Main Method / Entry Point *-*-*-*-*-*-* */
	public static void main(String[] args) 
	{
		TableBuilder2 run = new TableBuilder2();
		run.dataReader();
		run.initGUI();
	}	// End of main	
	
	// Not use at the moment
	public JPanel topTable()
	{
		JPanel yo = new JPanel();
		yo.setPreferredSize(new Dimension (600,50));
		JLabel me = new JLabel("Welcome to my Table");
		yo.add(me);
		
		return yo;
	}

	// Not use at the moment
	public JPanel botTable()
	{
		JPanel yo = new JPanel();
		yo.setPreferredSize(new Dimension (600,50));
		JLabel me = new JLabel("Have a Great Day!!");
		yo.add(me);
		
		return yo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JPanel table()
	{	setLayout(new BorderLayout());
		JPanel masterPanel = new JPanel(new BorderLayout());
//		JPanel masterPanel = new JPanel(new GridLayout(1,1));
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setPreferredSize(new Dimension(550,350));
		panel.setMinimumSize(new Dimension (500,350));	// 550,250
		GridBagConstraints gbc = new GridBagConstraints();

		/* Color Assignments and possible statuses from source file:
		 * 
		 * Status		Color		   Code			  Index
		 * Pending		White		255,255,255			0
		 * Completed	Green		 0 ,255, 0			1
		 * Executing	Yellow		255,255, 0			2
		 * Abended		Red			255, 0 , 0			3
		 * OnHold		Orange		255,128, 0			4
		 * OnRequest	Magenta		255, 0 ,255			5
		 * 
		 * NotRunning	->The schedule is disable for current run
		 * 
		 * */
		Color colors[] = { Color.WHITE, Color.GREEN, Color.YELLOW, 
				Color.RED, new Color(255,128,0), Color.MAGENTA };
		
		System.out.println(schedName.size());
		
		JComboBox<String> box[] = new JComboBox[schedName.size()];

		JPanel tPane[] = new JPanel[schedName.size()];
		
		for ( int i = 0; i < schedName.size(); i++ ) 
		{
			box[i] = new JComboBox(colors);
			box[i].setMaximumRowCount(6);
			box[i].setRenderer(new MyCellRenderer());
			box[i].setSelectedIndex(defaultColor(schedStatus.get(i)));
			
			tPane[i] = new JPanel();
			tPane[i].add(box[i]);
			
			TitledBorder border = BorderFactory.createTitledBorder(" " + schedName.get(i));
		    border.setTitlePosition(TitledBorder.CENTER);
			tPane[i].setBorder(border);
			tPane[i].setBackground(new Color(217,217,217));		//Light Grey color
			tPane[i].setPreferredSize(new Dimension(60,45));
			tPane[i].setMinimumSize(new Dimension(60,45));

			// Dealing with abends
/*			if (schedStatus.get(i).equals("Abended"))
			{
				if (!abends.equals("NONE"))
				{
					abends = abends + ", " + schedName.get(i) 
						+ " (" + schedMsg.get(i) + ") ";
				} else {
					abends = schedName.get(i) + " (" + schedMsg.get(i) + ") ";
				}
				System.out.println (abends);
				schedAbends.add(schedName.get(i) + " (" + schedMsg.get(i) + ") ");
				System.out.println (schedAbends);
			}*/
			
			int k = i;
			String a = schedName.get(i);
			
			/* Listener for color selection */
			box[i].addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					System.out.println("Selected: " + box[k].getSelectedIndex() + " " + a);
					String newAbend = "";
					
					if (box[k].getSelectedIndex() == 3)
					{
						try 
						{
							newAbend = (String)JOptionPane.showInputDialog(null ,"Job(s) abended: ",
									schedName.get(k) + " - Abend Information",
									JOptionPane.QUESTION_MESSAGE,null, null,
									schedMsg.get(k));
							System.out.println("Before: " + schedMsg.get(k));
							
							if (!newAbend.isEmpty())
							{
								//schedMsg.add(k,newAbend);//	It's set no add
								setAbend(newAbend,k);

						//} catch (NullPointerException e) { }	// Avoid cancel Error
					 
/*						String schName = schedName.get(k);
						for (int t = 0; t < schedAbends.size(); t++)
						{
							String name = schedAbends.get(t);
							int index = schedAbends.get(t).indexOf(" ");
							
							if ((index != -1) &&
							(name.substring(0,index)).equals(schName))
							{
								System.out.println("Existe");
								schedAbends.set(t,schName + " (" + newAbend + ") ");
								System.out.println("ARRAY: " + schedAbends);
								
							} else { System.out.println("No Existe"+index); }
						}*/
							}
	//				 	schedAbends.add(schedName.get(k) + " (" + schedMsg.get(k) + ") ");
						System.out.println("After: " + schedMsg.get(k));
						System.out.println("ARRAY: " + schedAbends);
						//}
							
						} catch (NullPointerException e) { }	// Avoid cancel Error
						System.out.println("After: " + schedMsg.get(k));
						System.out.println("ARRAY: " + schedAbends);
						
					} else {
						String schName = schedName.get(k);
						schedMsg.set(k,"");
						for (int t = 0; t < schedAbends.size(); t++)
						{
							String name = schedAbends.get(t);
							int index = schedAbends.get(t).indexOf(" ");
							
							if ((index != -1) &&
							(name.substring(0,index)).equals(schName))
							{
								System.out.println("Existe");
								//schedAbends.set(t,schName + " (" + newAbend + ") ");
								schedAbends.remove(t);//,schName + " (" + newAbend + ") ");
								
								System.out.println("ARRAY: " + schedAbends);
								
							} else { System.out.println("No Existe"+index); }
						}
					}
				}
			});
			
			/* Mouse Listener for click on box */
			tPane[i].addMouseListener(new MouseListener()
			{
				@Override
				public void mouseClicked(MouseEvent arg0) {
					System.out.println("Selected: " + a);
				}
				@Override
				public void mouseEntered(MouseEvent arg0) 	{	}
				@Override
				public void mouseExited(MouseEvent arg0) 	{	}
				@Override
				public void mousePressed(MouseEvent arg0) 	{	}
				@Override
				public void mouseReleased(MouseEvent arg0) 	{	}
			});
			//tPane[i].setFocusable(true);
			//tPane[i].addFocusListener(new FocusListener()
			box[i].setFocusable(true);
			box[i].addFocusListener(new FocusListener()
			{
				@Override
				public void focusGained(FocusEvent arg0) {
					//System.out.println("Focus");
					tPane[k].setBackground(Color.LIGHT_GRAY);
					repaint();
				}
				@Override
				public void focusLost(FocusEvent arg0) {
					//System.out.println("Loss Focus");
					tPane[k].setBackground(new Color(217,217,217));
					repaint();
				}
			});  
		} // End of For Loop

		/**
		 * CREATES/FILLS MY TABLE ON SCREEN - GRIDBAGLAYOUT
		 */
		int row = 0;
		int col = 0;
		for (int i = 0; i < schedName.size(); i++)
		{
			if (col == 8)	{	col = 0; row++;	}
			gbc.fill = GridBagConstraints.NONE; //HORIZONTAL;
			gbc.gridx = col;
			gbc.gridy = row;
			gbc.weightx = 0.1;		// Colums
			gbc.weighty = 0.1;
			panel.add(tPane[i],gbc);
			col++;	
		}
		/** ******* END GRIDBAGLAYOUT ***************	*/
		
		masterPanel.add(panel);
		//add(panel);
//		JLabel label = new JLabel("Abends information: " + abends);
//		masterPanel.add(label, BorderLayout.SOUTH);
		
		for (int a = 0; a < schedAbends.size(); a++)
		{
			if (a == 0) { 
				abends = schedAbends.get(a);
			} else {
				abends = abends + ", " + schedAbends.get(a);
			}
			
		}
		
		abendsInfo = new JLabel("Abends information: " + abends);
		masterPanel.add(abendsInfo, BorderLayout.SOUTH);
		//add(abendsInfo, BorderLayout.SOUTH);
		
		return masterPanel;
	} /* End of table()	*/

	/**
	 * Sets the abend information in the Arrays
	 * @param newAbend
	 * @param k
	 */
	public void setAbend(String newAbend, int k)
	{
		schedMsg.set(k,newAbend);
		String schName = schedName.get(k);
		for (int t = 0; t < schedAbends.size(); t++)
		{
			String name = schedAbends.get(t);
			int index = schedAbends.get(t).indexOf(" ");
			
			if ((index != -1) && (name.substring(0,index)).equals(schName))
			{
				System.out.println("Existe");
				
				schedAbends.set(t,schName + " (" + newAbend + ")");
				
				System.out.println("ARRAY: " + schedAbends);
				
			} else { System.out.println("No Existe" + index); }
		}
	}// End of setAbend
	
	public boolean checkAbend(int k){
		
		return false;
	}
	
	
	
	/**
	 * To be used...if neccesary.... to return regular status of a schedule
	 * Ex. When it runs...Always...May...On Request
	 * @return
	 */
	public String regStatus()	{	return null;	}		// Not use at the moment
	
	/**
	 * This method will return the index of the color
	 * to assign to each schedule
	 * @param status
	 * @return index (int)
	 */
	public int defaultColor(String status)
	{
	  switch (status)
	  {	
	  	case "Pending" :
	  		return 0;
		case "Completed" :
			return 1;
		case "Executing" :
			return 2;
		case "Abended" :
			return 3;
		case "OnHold" :
			return 4;
		case "OnRequest" :
			return 5;
		default:
			return 0;
		}
	}// End of defaultColor
	
	
	
	/**
	 *  Inner class to create the colors in the ComboBox
	 *
	 */
	@SuppressWarnings("rawtypes")
	class MyCellRenderer extends JButton implements ListCellRenderer
	{  
		public MyCellRenderer()	{	setOpaque(true);	}

		boolean data = false;

		@Override
		public void setBackground(Color newColor)
		{
			if (!data)	{	return;	}
			super.setBackground(newColor);
		}
		
	    public Component getListCellRendererComponent (
	    	JList colorList, Object color, int index, boolean isSelected, boolean cellHasFocus)  
	    {
	    	data=true;
	    	setText(""); 
	    	setBackground((Color)color);
	    	this.setPreferredSize(new Dimension (20,15));	// ComboBox size
	    	colorList.setFixedCellHeight(15);				// Changes popup CELL height
	    	data=false;
	    	return this;  
	    }
	}

	
/* *-*-*-*-*-*-* Text File Reader *-*-*-*-*-*-* */
	public void dataReader()
	{
//		File file = new File("C:/CAMESYS/DATA/PRSCHEDS/PRCCBMTH.txt");
//		File file = new File("C:/CAMESYS/DATA/PRSCHEDS/PRCSAMTH.txt");
//		File file = new File("C:/CAMESYS/DATA/PRSCHEDS/PRDAILY.txt");
//		File file = new File("C:/CAMESYS/DATA/PRSCHEDS/PRYEND.txt");
//		File file = new File("C:/CAMESYS/DATA/PRSCHEDS/PRCCBANN.txt");
//		File file = new File("C:/CAMESYS/DATA/PRSCHEDS/PRGSTMTH.txt");
		File file = new File("data/current/PROD/PRCCBMTH080517.txt");
		
		if(file != null)
		{
			BufferedReader reader = null;
			try 
			{
				reader = new BufferedReader(new FileReader(file));
				String line;
				while((line = reader.readLine()) != null)
				{
					String[] all = line.split("&");
					if (!all[2].equals("NotRunning"))
					{
						schedName.add(all[0]);			// First occurrence - Schedule name
						schedStatus.add(all[2]);		// Third occurrence - Schedule status
						schedMsg.add(all[3]);			// Fourth occurrence - Schedule message
						//System.out.println(all[2]);
						if(all[2].equals("Abended"))
						{
							schedAbends.add(all[0] + " (" + all[3] + ") ");
						}
					}
					//System.out.println(schedName + " " + schedStatus + " " + schedMsg);
				}
				System.out.println(schedAbends);
			} catch (FileNotFoundException e) {
				System.out.println("Error file not found: " + file.toString());
			} catch (IOException e) {
				System.out.println("Input/Output error, file: " + file.toString());
			} finally {
				if(reader != null)
				{
					try	{	reader.close();
					} catch (IOException e) {
						System.out.println("Input/Output error, file: " + file.toString());
					}
				}
			}
		}
		System.out.println(schedName);
	}// End of dataReader
	
}	/* *-*-*-* End of TableBuilder2.java *-*-*-* */
