//Hafsa Khalid 
//260672199 

import java.io.*;
import java.util.*;



public class main {     

     
    public static void main(String[] args) {
    //TODO:build the hash table and insert keys using the insertKeyArray function.
        // Chaining
        
        int firstListA = 554;
    	int[] firstList = {86, 85, 6, 97, 19, 66, 26, 14, 15, 49, 75, 64, 35, 54, 31, 9, 82, 29, 81, 13};
    	Chaining firstChain = new Chaining(10, 0, firstListA);
    	System.out.println("CHAINING - list X: " + firstChain.insertKeyArray(firstList));
    	
    	int secondListA = 1018;
    	int[] secondList = {52, 71, 34, 95, 68, 25, 44, 38, 47, 77, 92, 84, 94, 12, 61, 9, 89, 56, 28, 75};
    	Chaining secondChain = new Chaining(10, 0, secondListA);
    	System.out.println("CHAINING - list Y: " + secondChain.insertKeyArray(secondList));
    	
    	// Open Addressing
    	Open_Addressing firstOpen = new Open_Addressing(10, 0, firstListA);
    	System.out.println("OPEN ADDRESSING - list X: " + firstOpen.insertKeyArray(firstList));
    	
    	Open_Addressing secondOpen = new Open_Addressing(10, 0, secondListA);
    	System.out.println("OPEN ADDRESSING - list Y: " + secondOpen.insertKeyArray(secondList));

       

    
    }


}


    
