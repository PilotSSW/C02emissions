import java.io.*;
import java.util.*;


public class apriori {
    private boolean debug = false;
    private int itemsetSize = 1;
    private double minimum_support = .5; // Minimum support level set to 50%

    // Run apriori on each attribute in the attribute set
    public void apriori(ArrayList<List<String>> attributes, boolean debug){
        ArrayList<Object> attributesF1Itemsets = new ArrayList<Object>();

        for(int attribute=0; attribute < attributes.size(); attribute++){
            Map<String, Double> attributeItemset = createFrequentItemset(attributes.get(attribute), debug);
            attributesF1Itemsets.add(attributeItemset);
        }
    }

    // The apriori algorithm to run on each attribute 
    public Map<String, Double> createFrequentItemset(List<String> attribute, boolean debug)
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
            // Print for debugging

            double supportLevel = ((double) Collections.frequency(attribute, key) / attribute.size());
            if(debug) System.out.println(key + " : " + Collections.frequency(attribute, key) + " : Support Level : " + Collections.frequency(attribute, key) + "/" + attribute.size() + " - " + supportLevel + "%");
            if(supportLevel > minimum_support){
                instances.put(key, new Double(supportLevel));
            }
        }
        if(debug) System.out.println("");
        return instances;
    }


}