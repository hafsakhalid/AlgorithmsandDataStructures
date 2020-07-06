import java.util.ArrayList;
public class BellmanFord{

 
 /**
  * Utility class. Don't use.
  */
 public class BellmanFordException extends Exception{
  private static final long serialVersionUID = -4302041380938489291L;
  public BellmanFordException() {super();}
  public BellmanFordException(String message) {
   super(message);
  }
 }
 
 /**
  * Custom exception class for BellmanFord algorithm
  * 
  * Use this to specify a negative cycle has been found 
  */
 public class NegativeWeightException extends BellmanFordException{
  private static final long serialVersionUID = -7144618211100573822L;
  public NegativeWeightException() {super();}
  public NegativeWeightException(String message) {
   super(message);
  }
 }
 
 /**
  * Custom exception class for BellmanFord algorithm
  *
  * Use this to specify that a path does not exist
  */
 public class PathDoesNotExistException extends BellmanFordException{
  private static final long serialVersionUID = 547323414762935276L;
  public PathDoesNotExistException() { super();} 
  public PathDoesNotExistException(String message) {
   super(message);
  }
 }
 
    private int[] distances = null;
    private int[] predecessors = null;
    private int source;

    BellmanFord(WGraph g, int source) throws BellmanFordException{
        /* Constructor, input a graph and a source
         * Computes the Bellman Ford algorithm to populate the
         * attributes 
         *  distances - at position "n" the distance of node "n" to the source is kept
         *  predecessors - at position "n" the predecessor of node "n" on the path
         *                 to the source is kept
         *  source - the source node
         *
         *  If the node is not reachable from the source, the
         *  distance value must be Integer.MAX_VALUE
         *  
         *  When throwing an exception, choose an appropriate one from the ones given above
         */
        
        /* YOUR CODE GOES HERE */
      distances = new int [g.getNbNodes()]; 
      for(int i = 0; i<g.getNbNodes(); i++) { 
        distances[i] = Integer.MAX_VALUE; } 
      
      predecessors = new int [g.getNbNodes()]; 
      for(int i =0; i<g.getNbNodes(); i++) { 
        predecessors[i] = 0; } 
      
      int i = 0; 
      
      while (i < g.getNbNodes()-1) { 
        for (Edge e : g.listOfEdgesSorted()) { 
          if (distances[e.nodes[1]]>distances[e.nodes[0]]+e.weight) { 
            distances[e.nodes[1]] = distances[e.nodes[0]]+e.weight; 
              predecessors[e.nodes[1]] = e.nodes[0]; 
              
}
    
          }
        i++;
          
          }
              
        
  for (Edge j : g.listOfEdgesSorted()) { 
    if (distances[j.nodes[1]] != Integer.MAX_VALUE) { 
      if (distances[j.nodes[1]] > distances[j.nodes[0]] + j.weight ) {
        throw new NegativeWeightException("Negative Weight Cycle found"); }
  }
        
       }
    
    }
  

    public int[] shortestPath(int destination) throws BellmanFordException {
        /*Returns the list of nodes along the shortest path from 
         * the object source to the input destination
         * If not path exists an Exception is thrown
         * Choose appropriate Exception from the ones given 
         */

        /* YOUR CODE GOES HERE (update the return statement as well!) */
      
   
         
      ArrayList<Integer> backArray = new ArrayList<Integer>(); 
      
      int curr = destination; 
      while (curr != source ) { 
        if( distances[curr] == Integer.MAX_VALUE) { 
          throw new PathDoesNotExistException("Path does not exsit"); 
          
        }
        
        backArray.add(curr); 
        curr = predecessors[curr]; } 
                            
       int [] finalArray = new int[backArray.size()+1]; 
              finalArray[0] = source; 
                            for(int i =1; i< backArray.size()+1; i++) { 
                              finalArray[i] = backArray.get(backArray.size() -i); 
      
                            }
      
      
       return finalArray; 
         //return null;
    
    
    }

    public void printPath(int destination){
        /*Print the path in the format s->n1->n2->destination
         *if the path exists, else catch the Error and 
         *prints it
         */
        try {
            int[] path = this.shortestPath(destination);
            for (int i = 0; i < path.length; i++){
                int next = path[i];
                if (next == destination){
                    System.out.println(destination);
                }
                else {
                    System.out.print(next + "-->");
                }
            }
        }
        catch (BellmanFordException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        try{
            BellmanFord bf = new BellmanFord(g, g.getSource());
            bf.printPath(g.getDestination());
        }
        catch (BellmanFordException e){
            System.out.println(e);
        }

   } 
}
