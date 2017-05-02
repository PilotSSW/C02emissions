import java.io.*;
import java.util.*;

public class miner49 {
	public static boolean DEBUG_PARSER = false; 	// Debug turns on print statements for the output from the Parser
	public static boolean DEBUG_ATTRIBUTES = false; // Debug turns on print statements for the output from Apriori
	public static boolean DEBUG_APRIORI = false; 	// Debug turns on print statements for the output from each section
	public static double minimum_support = .4; 		// Minimum support level set to 50%
	public static ArrayList<List<String>> attributes; 

	public static void main(String[] args) throws Exception {
		try{
			try {
				minimum_support = Double.parseDouble(args[1]);
				if(args[2].equals("debug_parser")){
                	DEBUG_PARSER = true;
				}
				else if(args[2].equals("debug_attributes")){
                	DEBUG_ATTRIBUTES = true;
                }
                else if(args[2].equals("debug_apriori")){
                	DEBUG_APRIORI = true;
                }
                else if(args[2].equals("debug_all")){
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

			// Initialize a new apriori object and itemize each of the attributes in the attribute set
			apriori itemized = new apriori();
			itemized.apriori(attributes, DEBUG_APRIORI, minimum_support);

			if(DEBUG_APRIORI) itemized.printItemset();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Failed to analyze dataset");
		}
		
	}
}