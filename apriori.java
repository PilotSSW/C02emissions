import java.io.*;
import java.util.*;


public class apriori {
    private boolean debug_unfiltered = false;
    private boolean debug_filtered = false;
    private int itemsetSize = 1;

    ArrayList<Map<String, Double>> attributesF1Itemsets = new ArrayList<Map<String, Double>>();
    ArrayList<Map<String, Double>> attributesF2Itemsets = new ArrayList<Map<String, Double>>();
    ArrayList<Map<String, Double>> attributesF3Itemsets = new ArrayList<Map<String, Double>>();

    // Run apriori on each attribute in the attribute set
    public void apriori(ArrayList<List<String>> attributes, boolean debug, double minimum_support){

        // Generate the frequent 1 itemset 
        for(int attribute=0; attribute < attributes.size(); attribute++){
            attributesF1Itemsets.add(createFrequentItemset(attributes.get(attribute), debug, minimum_support));
        }

        System.out.println("Frequent Itemset2");
        // Generate the frequent 2 itemset 
        for(int attribute1=0; attribute1 < attributes.size(); attribute1++){
            for(int attribute2=0; attribute2 < attributes.size(); attribute2++){
                attributesF2Itemsets.add(createFrequentTwoItemset(attributeF1Itemset, attributes.get(attribute1), attributes.get(attribute2), debug, minimum_support) debug, minimum_support))
            }
        }
        /*
        for(int attribute1=0; attribute1 < attributesF1Itemsets.size(); attribute1++){
            for(int attribute2=0; attribute2 < attributesF1Itemsets.size(); attribute2++){
                Map<String, Double> attributeItemset = createFrequentTwoItemset(attributesF1Itemsets.get(attribute1), attributesF1Itemsets.get(attribute2), debug, minimum_support);
                attributesF2Itemsets.add(attributeItemset);
            }
        }*/
    }

    // The apriori algorithm to run on each attribute 
    public Map<String, Double> createFrequentItemset(List<String> attribute, boolean debug, double minimum_support)
    {   
        // Print out the attribute
        if(debug) System.out.println("Attribute: " + attribute.get(0));

        // Create a list with the distinct elements using stream.
        HashSet<String> unique = new HashSet<String>(attribute);

        // Remove the attribute name from the instances 
        unique.remove(attribute.get(0));

        // Remove the null instances in the itemset
        while(unique.remove("?")){}

        // Create a HashMap with the instance, the number of times it's seen and it's support level
        Map<String, Double> instances = new HashMap<>();

        // Loop through each of the unqiue instances in the attribute
        for (String key : unique) {
            double supportLevel = ((double) Collections.frequency(attribute, key) / attribute.size());
            
            // Print for debugging
            if(debug) System.out.println("      " + String.format("%1$-" + 35 + "s", key) + " : Support Level : " + Collections.frequency(attribute, key) + "/" + attribute.size() + " - " + supportLevel + " %");
            if(supportLevel > minimum_support){
                instances.put(key, new Double(supportLevel));
            }
        }
        if(debug) System.out.println("");
        return instances;
    }

    // The apriori algorithm to run on each attribute 
    public Map<String, Double> createFrequentTwoItemset(Map<String, Double> f1Itemset, List<String> attribute1, List<String> attribute2, boolean debug, double minimum_support)
    {   
        System.out.println("Attribute1: " + attribute1.get(0));
        System.out.println("Attribute2: " + attribute2.get(0));
    }

    public void printItemset(ArrayList<List<String>> attributes){
        System.out.println("\nFiltered Attribute Instances \n");

        for(int attribute=0; attribute < attributesF1Itemsets.size(); attribute++){
            System.out.println("Attribute: " + attributes.get(attribute).get(0));
            for (Map.Entry<String, Double> key : attributesF1Itemsets.get(attribute).entrySet()) {
                System.out.print("                                                                                  " + String.format("%1$-" + 35 + "s", key.getKey()) + ": ");
                System.out.println(key.getValue() + " %");
            }
        }
    }
}