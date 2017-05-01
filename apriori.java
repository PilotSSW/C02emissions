import java.io.*;
import java.util.*;


public class apriori {
    private boolean debug = false;
    private int itemsetSize = 1;

    // Run apriori on each attribute in the attribute set
    public void apriori(ArrayList<List<String>> attributes, boolean debug, double minimum_support){
        ArrayList<Map<String, Double>> attributesF1Itemsets = new ArrayList<Map<String, Double>>();

        for(int attribute=0; attribute < attributes.size(); attribute++){
            Map<String, Double> attributeItemset = createFrequentItemset(attributes.get(attribute), debug, minimum_support);
            attributesF1Itemsets.add(attributeItemset);
        }

        if(debug){
            System.out.println("\nFiltered Attribute Instances \n");

            for(int attribute=0; attribute < attributesF1Itemsets.size(); attribute++){
                System.out.println("Attribute: " + attributes.get(attribute).get(0));
                for (Map.Entry<String, Double> key : attributesF1Itemsets.get(attribute).entrySet()) {
                    System.out.print("                                                                                  " + key.getKey() + ": ");
                    System.out.println(key.getValue());
                }
            }
        }
    }

    // The apriori algorithm to run on each attribute 
    public Map<String, Double> createFrequentItemset(List<String> attribute, boolean debug, double minimum_support)
    {   
        // Print out the attribute
        if(debug) System.out.println("Attribute: " + attribute.get(0));

        // Remove the null instances in the itemset
        while(attribute.remove("?")){}

        // Create a list with the distinct elements using stream.
        HashSet<String> unique = new HashSet<String>(attribute);

        // Remove the attribute name from the instances 
        unique.remove(attribute.get(0));

        // Create a HashMap with the instance, the number of times it's seen and it's support level
        Map<String, Double> instances = new HashMap<>();

        // Loop through each of the unqiue instances in the attribute
        for (String key : unique) {
            double supportLevel = ((double) Collections.frequency(attribute, key) / attribute.size());
            
            // Print for debugging
            if(debug) System.out.println("      " + String.format("%1$-" + 35 + "s", key) + " : Support Level : " + Collections.frequency(attribute, key) + "/" + attribute.size() + " - " + supportLevel + "%");
            if(supportLevel > minimum_support){
                instances.put(key, new Double(supportLevel));
            }
        }
        if(debug) System.out.println("");
        return instances;
    }


}