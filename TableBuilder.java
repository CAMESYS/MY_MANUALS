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
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;


public class TableBuilder extends JPanel
{
	JFrame mainFrame;
	ArrayList<String> name = new ArrayList<>();
	
	public TableBuilder()	{ 	/*initGUI();*/	}
	
	public void initGUI()	//{	//this.add(table());
	{
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
		TableBuilder run = new TableBuilder();
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
	{	
		JPanel masterPanel = new JPanel(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setPreferredSize(new Dimension(550,350));
		panel.setMinimumSize(new Dimension (500,350));	// 550,250
		GridBagConstraints gbc = new GridBagConstraints();

		Color colors[] = { Color.WHITE, Color.GREEN, Color.YELLOW, 
				Color.RED, new Color(255,128,0), Color.MAGENTA };
		
		System.out.println(name.size());
		
		JComboBox<String> box[] = new JComboBox[name.size()];

		JPanel tPane[] = new JPanel[name.size()];
		
		for ( int i = 0; i < name.size(); i++ ) 
		{
			box[i] = new JComboBox(colors);
			box[i].setMaximumRowCount(6);
			box[i].setRenderer(new MyCellRenderer());
			
			tPane[i] = new JPanel();
			tPane[i].add(box[i]);
			
			TitledBorder border = BorderFactory.createTitledBorder(" " + name.get(i));
		    border.setTitlePosition(TitledBorder.CENTER);
			tPane[i].setBorder(border);
			tPane[i].setBackground(new Color(217,217,217));
			tPane[i].setPreferredSize(new Dimension(60,45));
			tPane[i].setMinimumSize(new Dimension(60,45));

			int k = i;
			String a = name.get(i);
			
			/* Listener for color selection */
			box[i].addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.print("Selected: " + box[k].getSelectedIndex() + " " + a);
					System.out.print(" ComboBox Size: " + tPane[k].getWidth() + " " 
						+ tPane[k].getHeight());
					System.out.print(" Panel: " + panel.getWidth() + " " + panel.getHeight());
					System.out.println(" Master Panel: " + masterPanel.getWidth() 
						+ " " + masterPanel.getHeight());
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
			
/*			FocusListener focused = new FocusListener()
			{
				@Override
				public void focusGained(FocusEvent e) {
//	              jpanel.setBackground(Color.BLACK); // Assuming an old command?
					tPane[k].setBackground(Color.YELLOW);
					repaint();
				}
				@Override
				public void focusLost(FocusEvent e) {
					tPane[k].setBackground(new Color(217,217,217));
					repaint();
				}
			};
			tPane[i].setFocusable(true);
			tPane[i].addFocusListener(focused);
*/
	      
		} // End of For Loop

		/**
		 * CREATES/FILLS MY TABLE ON SCREEN - GRIDBAGLAYOUT
		 */
		int row = 0;
		int col = 0;
		for (int i = 0; i < name.size(); i++)
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
		JLabel label = new JLabel("Abends information: ");
		masterPanel.add(label, BorderLayout.SOUTH);
		
		return masterPanel;			//return panel;
	} /* End of table()	*/

	/**
	 * To be used...if neccesary.... to return regular status of a schedule
	 * Ex. When it runs...Always...May...On Request
	 * @return
	 */
	public String regStatus()	{	return null;	}		// Not use at the moment
	
	/**
	 *  Inner class to create the colors in the ComboBox
	 *
	 */
//	@SuppressWarnings("rawtypes")
	class MyCellRenderer extends JButton  implements ListCellRenderer
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
		File file = new File("data/master/PRCCBMTH.txt");
		
		if(file != null)
		{
			BufferedReader reader = null;
			try 
			{
				reader = new BufferedReader(new FileReader(file));
				String line;
				while((line = reader.readLine()) != null)
				{
					String[] all = line.split(",");
					name.add(all[2]);				// Third occurrence - Schedule name
				}
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
		System.out.println(name);
	}
	
}	/* *-*-*-* End of FileReader.java *-*-*-* */




/* MATRIX EXAMPLE
 * while (in.hasNextLine()) {
    Scanner lineIn = new Scanner(line);
    //The initial case - this first line is used to determine the size of the array
    if(lineIn.hasNext()) {
        //Create a String array by splitting by spaces
        String[] s = lineIn.nextLine().split(" ");
        //Reinitialize the array to hold all of your subarrays
        matrix = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            //Reinitialize each subarray to hold the numbers
            matrix[i] = new int[i];
            //Finally, parse your data from the String array
            matrix[0][i] = Integer.parseInt(s[i]);
        }
    }
    //Repeat the steps now that all of your arrays have been initialized
    for (int j = 1; j < matrix.length; j++) {
        String[] s = lineIn.nextLine().split(" ");
        for (int i = 0; i < s.length; i++) {
            matrix[j][i] = Integer.parseInt(s[i]);
        }
    }
}
*/

/**
 * CREATES MY TABLE ON SCREEN - WHEN USING GRIDPANE JAVAFX
 */
/*		int row = 0;
int col = 0;
for (int i = 0; i < name.size(); i++)
{
	if (col == 8)	{	col = 0; row++;	}

	panel.add(tPane[i], col, row);

	col++;	
}*/
//panel.setBorder(new EmptyBorder(5,5,5,5));

//int row = 0;
//int col = 0;


/**
 * Sets the size of the rectangle showing the colors - FX
 * 
 */
/*	static class ColorRectCell extends ListCell<String>{
	@Override
	public void updateItem(String item, boolean empty){
		super.updateItem(item, empty);
		Rectangle rect = new Rectangle(20,15);	// size of color rectangle 30,15 Original
		
		if(item != null)
		{
			rect.setFill(Color.web(item));
			setGraphic(rect);
		}
	}
}*/

/*	public GridLayout newLayout()	
{
	GridLayout layout; // = new GridLayout();		// Height, Width
	if (name.size() <= 7)
	{
		layout = new GridLayout(1,7);
		//layout.setHgap(7);	layout.setVgap(7);
	} else if ((name.size() > 7) && (name.size() <= 14)) {	
		layout = new GridLayout(2,7);
		//layout.setHgap(5);	layout.setVgap(4);
	} else {
		layout = new GridLayout(7,7);
		//layout.setHgap(7);	layout.setVgap(7);
	}
	
	return layout;
}
*/
