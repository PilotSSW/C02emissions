import java.io.*;
import java.util.*;


public class apriori {
    private boolean DEBUG = false;

    public static void main(String[] args) throws Exception {
        apriori ap = new apriori(args);
    }



    /** This is the main interface to use this class as a library */
    public apriori(String[] args) throws Exception
    {


    }

    private void createItemsetsOfSize1() {
        itemsets = new ArrayList<int[]>();
        for(int i=0; i<numItems; i++)
        {
            int[] cand = {i};
            itemsets.add(cand);
        }
    }
    
    private void calculateFrequentItemsets() throws Exception
    {
        
       
    }
}