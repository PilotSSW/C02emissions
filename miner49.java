import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class miner49 {
	public static ArrayList<List<String>> attributes; 
	public static void main(String[] args) throws Exception {
		System.out.println("Hello from Miner49");

		// Parse the input csv file
		csvparser parsedData = new csvparser(args);

		// Convert the parsed input into a list of attributes
		attributes = parsedData.columnToRow();

		// Print the attributes
		printAttributes();
		
	}

	public static void printAttributes(){
		// Print the list 

		// Iterate over each attribute 
		for(int i=0; i<attributes.size(); i++){
			// Print the attribute title
			System.out.println(attributes.get(i).get(1));
			// Iterate over each value in the attributes
			for(int j=2; j<attributes.get(i).size(); j++){
				System.out.print(attributes.get(i).get(j) + " ");
			}
			System.out.println("");
		}
	}
}