import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/* *************************************************** */	
/* *-*-*-*-*-*-* Sets Team's Logo on top *-*-*-*-*-*-* */
/* *************************************************** */

/**
 * This class will load the Team Logo onto the main screen
 * 
 * @author CAMESYS
 */
public class LogoLoader extends JPanel
{
	
	/* *-*-*-*-*-*-* Constructor *-*-*-*-*-*-* */
	public LogoLoader() {	initGUI();	}
	
	/* *-*-*-*-*-*-* initGUI generates the view *-*-*-*-*-*-* */
	public void initGUI()	{	this.add(teamLogo());	}
	
	/* *-*-*-*-*-*-* Creates the Team's Logo to be displayed in the GUI *-*-*-*-*-*-* */
	public JLabel teamLogo()
	{
		BufferedImage resizedImage;
		try
		{
			// The ideal dimensions for TEAMLOGO.PNG are 800 x 100 (It's a banner)
			// in order to get a clear result. Any image can be renamed to TEAMLOGO.PNG
			final BufferedImage loadLogo = ImageIO.read(new File("images/TEAMLOGO.PNG"));
			
			if (loadLogo != null)
			{
				resizedImage=resize(loadLogo,DailyNews.mainScreen.getWidth(),50);
				ImageIcon icon = new ImageIcon(resizedImage);
				JLabel newLogo = new JLabel(icon);
				newLogo.setBorder(new EmptyBorder(0,5,0,10));
					
				// Listener to resize logo according to main screen size
				// DailyNews.mainScreen = mainScreen JFrame from class DailyNews
				DailyNews.mainScreen.addComponentListener(new ComponentAdapter() 
				{  
			        public void componentResized(ComponentEvent evt) {
			        	ImageIcon icon = new ImageIcon(
			        			resize(resizedImage,DailyNews.mainScreen.getWidth(),50));
			        	newLogo.setIcon(icon);
			        }
				});
					return newLogo;
			}  
		} catch (IOException e) {
			System.out.println("Team Logo not loaded, file SEMLOGO.PNG not found...");
		}
		return null;
	}// End of image
	
	/* *-*-*-*-*-*-* Resizes the logo according with the parameters provided *-*-*-*-*-*-* */
	public static BufferedImage resize(BufferedImage image, int width, int height)
	{
	    BufferedImage logo = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D newGraph = (Graphics2D) logo.createGraphics();
	    newGraph.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, 
	    		RenderingHints.VALUE_RENDER_QUALITY));
	    newGraph.drawImage(image, 0, 0, width, height, null);
	    newGraph.dispose();
	    return logo;
	}// End of resize

}	/* *-*-*-* End of LogoLoader.java *-*-*-* */
