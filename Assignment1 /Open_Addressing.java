//Hafsa Khalid 
//260672199 

import java.io.*;
import java.util.*;

public class Open_Addressing {
     public int m; // number of SLOTS AVAILABLE
     public int A; // the default random number
     int w;
     int r;
     public int[] Table;

     protected Open_Addressing(int w, int seed, int A) {

         this.w = w;
         this.r = (int) (w-1)/2 +1;
         this.m = power2(r);
         if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
         }
        else{
            this.A = A;
        }
         this.Table = new int[m];
         for (int i =0; i<m; i++) {
             Table[i] = -1;
         }
         
     }
     
                 /** Calculate 2^w*/
     public static int power2(int w) {
         return (int) Math.pow(2, w);
     }
     public static int generateRandom(int min, int max, int seed) {     
         Random generator = new Random(); 
                 if(seed>=0){
                    generator.setSeed(seed);
                 }
         int i = generator.nextInt(max-min-1);
         return i+min+1;
     }
        /**Implements the hash function g(k)*/
        public int probe(int key, int i) {
            //TODO: implement this function and change the return statement.
            int h_hash = (this.A * key) % (power2(this.w)) >> (w-r);
            int g_hash = (h_hash + i) % (power2(this.r));
                return g_hash;
     }

     /**helper method to check slot n is empty **/

        public boolean isEmpty(int hashValue) { 
            return Table[hashValue] == -1;
        }
     
     
     /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int insertKey(int key){
            //TODO : implement this and change the return statement.

            for (int i = 0; i< this.m; i++) {
                int index = probe(key, i); 

                if (isEmpty(index) == true) { 
                    this.Table[index] = key; 
                    return i; 
                        }

                        else {
                            continue; 
                        }
            }
            return this.m;  
        }
        
        /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
        public int insertKeyArray (int[] keyArray){
            //TODO
            int collision = 0;
            for (int key: keyArray) {
                collision += insertKey(key);
            }
            return collision;
        }
            
         /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int removeKey(int key){
             //TODO: implement this and change the return statement
            boolean removed = false; 
            for(int i = 0; i<this.m; i++) {
                int index = probe(key, i); 
                if (this.Table [index] == key) {
                    this.Table[index] = -1; 
                    removed = true; 
                    return i; 
                }
                else { 
                    continue; }
                
                
        }
            if(removed == false) {

            }
            
            return this.m;
        }
}            

            
       
