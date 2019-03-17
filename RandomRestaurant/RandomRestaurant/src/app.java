import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class app
{
	/*
	 * Reads a text file and returns a list of restaurants
	 * Each restaurant name should be on a separate line
	 */
	public static void main(String[] args) throws Exception
	{
		Scanner scanner = new Scanner(System.in);
		BufferedReader reader = null;

		Random random = new Random();
		
		while(true)
		{
			System.out.println("Enter the name of the list file: ");
			String fileName = scanner.nextLine().trim();
			
			//catch exception if file is not found
			try
			{
				reader = new BufferedReader(new FileReader(new File(fileName)));
				break;
			}
			catch(Exception e)
			{
				System.out.println("File Not Found. Please try again.");
			}
		}
		
		//String path = "C:\\Users\\mrr17\\Documents\\Eclipse\\RandomRestaurant\\RestaurantList.txt";
		
		
		
		
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> foundList = new ArrayList<String>();
		
		
		
		
		
		//this loop creates the list
		String restaurant;
		while((restaurant = reader.readLine()) != null)
		{
			list.add(restaurant);
		}
		
		
		while(true)
		{
			System.out.println("Would you like to find a restaurant? (y/n)");
			boolean decision = processInput();
			
			if(decision)
			{
				break;
			}
			else if(!decision)
			{
				System.out.println("Bye bye then.");
				
				reader.close();
				System.exit(0);
			}
		}
		
		//loop for finding and suggesting a restaurant
		while(true)
		{
			//generates a random number between 0 and size-1
			int rand = random.nextInt(list.size());
			restaurant = list.get(rand);
			
			boolean finding = checkDuplicates(restaurant, foundList);
			//if finding == true, look for a different restaurant		
			
			//loop for suggesting a restaurant
			while(!finding)
			{
				System.out.println("How about " + restaurant + "? (y/n)");
				boolean decision = processInput();
				
				if(decision)
				{
					System.out.println("Enjoy!");
					
					reader.close();
					System.exit(0);
				}
				else if(!decision)
				{
					foundList.add(restaurant);
					
					//if every restaurant was rejected, start over
					if(list.size() == foundList.size())
					{
						System.out.println("You rejected every single restaurant. Let's start over.");
						foundList.clear();
					}
					
					break;
				}	
			}
			
		}
		
	}
	
	/*
	 * Checks the random restaurant with the list of already rejected restaurants
	 * Returns true if duplicate is found (should find new restaurant)
	 * Returns false is no duplicate is found
	 */
	
	public static boolean checkDuplicates(String restaurant, ArrayList<String> foundList)
	{
		for(int i = 0; i < foundList.size(); i++)
		{
			//if we already found the restaurant, don't want duplicates
			if(restaurant == foundList.get(i))
			{
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * Prompted after asking the user to make a decision (yes or no)
	 * Returns true if user chose yes, false if user chose no
	 */
	public static boolean processInput()
	{
		Scanner scanner = new Scanner(System.in);
		
		while(true)
		{
			String input = scanner.nextLine().toLowerCase().trim();
			
			if(input.equals("y") || input.equals("yes"))
			{
				return true;
			}
			else if(input.equals("n") || input.equals("no"))
			{
				return false;
			}
			else if(input.equals("quit"))
			{
				System.exit(0);
			}
			else
			{
				System.out.println("Type yes or no (y/n) to make a decision.");
			}
		}
	}
		
}
