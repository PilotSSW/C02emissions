import java.io.*;
import java.util.*;


public class apriori {
    private boolean DEBUG = false;
    private int itemsetSize = 1;

    // Run apriori on each attribute in the attribute set
    public void apriori(ArrayList<List<String>> attributes){
        System.out.println("APRIORI!!!");
        for(int attribute=0; attribute < attributes.size(); attribute++){
            aprioriAttribute(attributes.get(attribute));
        }
    }

    // The apriori algorithm to run on each attribute 
    public void aprioriAttribute(List<String> attribute)
    {   
        System.out.println("Attribute: " + attribute.get(0));
        for(int item=1; item < attribute.size(); item++){
            System.out.println(attribute.get(item));
        }
    }

    private void createItemsetsOfSize1() 
    {

    }
    
    private void calculateFrequentItemsets()
    {
       
    }
}