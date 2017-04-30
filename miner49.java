import java.io.*;
import java.util.*;

public class miner49 {
	public static boolean DEBUG_PARSER = false; // Debug turns on print statements for the output from the Parser
	public static boolean DEBUG_ATTRIBUTES = false; // Debug turns on print statements for the output from Apriori
	public static boolean DEBUG_APRIORI = false; // Debug turns on print statements for the output from each section
	public static ArrayList<List<String>> attributes; 

	public static void main(String[] args) throws Exception {
		try{
			try {
				if(args[1].equals("debug_parser")){
                	DEBUG_PARSER = true;
				}
				else if(args[1].equals("debug_attributes")){
                	DEBUG_ATTRIBUTES = true;
                }
                else if(args[1].equals("debug_apriori")){
                	DEBUG_APRIORI = true;
                }
                else if(args[1].equals("debug_all")){
                	DEBUG_PARSER = true;
                	DEBUG_ATTRIBUTES = true;
                	DEBUG_APRIORI = true;
                }
            }
            catch(Exception e){

            }
			String[] newargs = new String[args.length + 1];
			newargs[0] = args[0]; 
			newargs[1] = String.valueOf(DEBUG_PARSER);
			// Parse the input csv file
			csvparser parsedData = new csvparser(newargs);

			// Convert the parsed input into a list of attributes
			attributes = parsedData.columnToRow();

			// Print the attributes and write them to a file if debugging is enabled
			if(DEBUG_ATTRIBUTES){
				parsedData.printAttributes();
				parsedData.writeToFile();
			}
			String[] attr = parsedData.makeAttributeSet();

			bin binnedAttributes = new bin(attr);

			//apriori runtest = new apriori(attr);
			
			// Run the apriori implementation 
			if(DEBUG_APRIORI){
				System.out.println("Go fuck yourself");
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Failed to analyze dataset");
		}
		
	}
}