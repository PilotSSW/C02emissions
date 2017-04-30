import java.io.*;
import java.util.*;

public class bin {
	// Static var holds the default number of bins to split an attribute set on
	public static int defaultNumberofBins = 5; 

	public String[] bin(String[] args){
		String[] returnArray = new String[1];
		returnArray[0] = "";
		//int binLargestLength = findLargestBin(args);
		for(int i=0; i<args.length; i++){
			System.out.println(args[i]);
			//binnify(args[i], binLargestLength);
		}
		return returnArray;
	}

	/*
	public int findLargestBin(String[] attributes){
		int largestAttribute = 0;
		for (int attribute=0; attribute < attributes.length; attribute++){
			if(attribute > largestAttribute){
				largestAttribute = attribute;
			}
		}
		return largestAttribute;
	}

	public String[] binnify(String[] attribute, int maxBinLength){
		
		int numberOfBins = (Math.floor(attribute.length / maxBinLength)) * defaultNumberofBins; 
		String[] bins = new String[numberOfBins];

		for(int bin=0; bin < numberOfBins; bin++){
			for(int arrayval=1; arrayval < (attribute.length / numberOfBins) + 1; arrayval++){
				System.out.print(attribute[arrayval]);
			}
			System.out.println("");
		}
	}
	*/
}