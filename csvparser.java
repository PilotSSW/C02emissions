import java.io.*;
import java.util.*;

public class csvparser {
    public class ParserException extends Exception
    {
      public ParserException(String message)
      {
        super(message);
      }
    }
    private boolean DEBUG = false;          // Debug parameter
    private char DEFAULT_SEPARATOR = ',';  // Split value
    private char DEFAULT_QUOTE = '"';      // Quote sign
    private int HEADER_ROW = 0;            // Row that contains the column headers

    public ArrayList<List<String>> csvGrid = new ArrayList<List<String>>(); // Holds the rows of the parsed CSV file 
    public ArrayList<List<String>> parsedGrid = new ArrayList<List<String>>(); // Holds the columns of the parsed CSV file 

    public csvparser(String[] args) throws ParserException {
        try {
            String csvFile = args[0];
            try {
                DEBUG = Boolean.parseBoolean(args[1]);
            }
            catch(Exception e){

            }
            Scanner scanner = new Scanner(new File(csvFile));
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                csvGrid.add(line);
            }
            scanner.close();
        }
        catch(Exception e){
            System.out.println("Invalid File specified");
        }
    }

    public List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    public List<String> parseLine(String cvsLine, char separators, char customQuote) {
        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }
        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }
        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;
        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {
            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {
                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }
                }
            } else {
                if (ch == customQuote) {
                    inQuotes = true;
                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }
                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }
                } else if (ch == separators) {
                    result.add(curVal.toString());
                    curVal = new StringBuffer();
                    startCollectChar = false;
                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }
        }
        result.add(curVal.toString());
        return result;
    }

    public ArrayList<List<String>> columnToRow(){
        int numRows = 0;
        // Loop through each of the column headers
        if(DEBUG){
            System.out.println(" --------- PARSER ATTRIBUTE CONVERSION OUTPUT --------- ");
        } 
        for(int i=0; i<csvGrid.get(HEADER_ROW).size(); i++){
            ArrayList<String> line = new ArrayList<String>();
            // Loop through each of the rows of data
            for(int j=0; j<csvGrid.size(); j++){
                line.add(csvGrid.get(j).get(i));
            }
            parsedGrid.add(line);
            if(DEBUG == true){
                System.out.println(line);
            }
            numRows++;
        }
        if(DEBUG == true)
        {
            System.out.println("Read " + numRows + " total attributes from csv file");
        }
        return parsedGrid;
    }

    public String[] makeAttributeSet(){
        ArrayList<String> attrSet = new ArrayList<String>();
        for(int i=0; i<parsedGrid.size(); i++){
            // Iterate over each value in the attributes
            ArrayList<String> line = new ArrayList<String>();
            for(int j=0; j<parsedGrid.get(i).size(); j++){
                line.add(parsedGrid.get(i).get(j) + " ");
            }
            String attribute = String.join("",line);
            attrSet.add(attribute);
        }
        return attrSet.toArray(new String[attrSet.size()+1]);
    }

    public void printAttributes(){
        // Print the list 

        // Iterate over each attribute 
        System.out.println("Number of attributes: " + parsedGrid.size());
        for(int i=0; i<parsedGrid.size(); i++){
            // Set if there are missing attribute values for this attribute
            boolean missingValues = false;

            // Print the attribute title
            System.out.println("---" + parsedGrid.get(i).get(0) + "---");

            // Iterate over each value in the attributes
            for(int j=1; j<parsedGrid.get(i).size(); j++){
                if(parsedGrid.get(i).get(j).equals("?")){
                    missingValues = true;   
                }
                else {
                    if(missingValues == true){
                        System.out.print("[---Missing Values---] ");
                        missingValues = false;
                    }
                    System.out.print(parsedGrid.get(i).get(j) + " ");
                }
            }
            System.out.println("\n");
        }
    }

    public void writeToFile(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("attributes.txt"))) {
            for(int i=0; i<parsedGrid.size(); i++){
                for(int j=0; j<parsedGrid.get(i).size(); j++){
                    bw.write(parsedGrid.get(i).get(j) + " ");
                }
                bw.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}