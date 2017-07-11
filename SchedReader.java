import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class will read a schedule and place contents in 
 * arrays to be process when building display tables
 * 
 * @author CAMESYS
 */
public class SchedReader
{
	/* *-*-*-*-*-*-* Attributes *-*-*-*-*-*-* */
	ArrayList<String> schedName 	= new ArrayList<>();
	ArrayList<String> schedNameTwo 	= new ArrayList<>();
	ArrayList<String> schedStatus 	= new ArrayList<>();
	ArrayList<String> schedMsg 		= new ArrayList<>();
	ArrayList<String> schedAbends 	= new ArrayList<>();
	ArrayList<String> schedDay 		= new ArrayList<>();
	String path = "data/current/";
	String schedule;
	
	/* *-*-*-*-*-*-* Constructor *-*-*-*-*-*-* */
	public SchedReader()
	{
		schedule = path + "PROD/PRCCBMTH080517.txt";
		arrayBuilder();
		// Return arrays to the table builder
	}
	
	/* *-*-*-*-*-*-* Parameterized Constructor *-*-*-*-*-*-* */
	public SchedReader(String file)
	{
		schedule = file;
		arrayBuilder();
	}
	
	/* *-*-*-*-*-*-* File Reader and Array Filler *-*-*-*-*-*-* */
	public void arrayBuilder()
	{
	//	File file = new File("data/current/PROD/PRCCBMTH080517.txt");
		File file = new File(path + schedule);
	
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
				if (!all[2].equals("NotRunning"))	// Only schedules meant to run
				{									// (Ex.OnRequest = NotRunning )
					// File structure				v Separator
					// ScheduleName & ScheduleName2 & Status & Message & DayOfWeek
					//		[0]				[1]			[2]		[3]			[4]	-->Array Ref
					
					schedName.add(all[0]);
					schedNameTwo.add(all[1]);
					schedStatus.add(all[2]);
					schedMsg.add(all[3]);
					schedDay.add(all[4]);
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
			// Create error window
			
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
	}// End of arrayBuilder
	
	/* *-*-*-*-*-*-* Array Getters to pass arrays to another class *-*-*-*-*-*-* */
	public ArrayList<String> getNameOne()	{ return schedName;		}
	public ArrayList<String> getNameTwo()	{ return schedNameTwo;	}
	public ArrayList<String> getStatus()	{ return schedStatus;	}
	public ArrayList<String> getMsg()		{ return schedMsg;		}
	public ArrayList<String> getAbends()	{ return schedAbends;	}
	public ArrayList<String> getDay()		{ return schedDay;		}
	
}	/* *-*-*-* End of SchedReader.java *-*-*-* */
