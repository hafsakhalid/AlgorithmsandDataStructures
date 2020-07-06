import java.io.*;
import java.util.*;




public class FordFulkerson {

 
 public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
  ArrayList<Integer> Stack = new ArrayList<Integer>();
  /* YOUR CODE GOES HERE*/ 
  WGraph copy = new WGraph(graph); 
  int nbNodes = copy.getNbNodes(); 
  Stack.add(source); 
  int find_node = source; 
  int getRid_node;
  
  while(find_node != -1) { 
    for(int j = 0; j<nbNodes; j++) { 
      if(copy.getEdge(find_node,j) != null && copy.getEdge(find_node,j).weight > 0) { 
        Stack.add(j); 
        
        copy.setEdge(find_node,j, 0); 
        if(j == destination) {
          find_node = -1; 
          break; 
          
        } 
        
        find_node = j; 
        break; 
        
      }
      
      else if(j == nbNodes -1) { 
        getRid_node = Stack.get(Stack.size() -1); 
        Stack.remove(Stack.size() -1); 
        if(Stack.size() == 0) { 
          return Stack; }
        
        find_node = Stack.get(Stack.size() -1); 
        
      }
        
      
    }
    
  }
  
   return Stack; 
      
 }
      
      
      

 
 
 
 public static void fordfulkerson(Integer source, Integer destination, WGraph graph, String filePath){
  String answer="";
  String myMcGillID = "260672199"; //Please initialize this variable with your McGill ID
  int maxFlow = 0;
  
    /* YOUR CODE GOES HERE*/ 
  
  ArrayList<Integer> Path = new ArrayList<Integer>(); 
  WGraph flow = new WGraph(graph); 
   for (Edge e : flow.getEdges()) { 
     e.weight = 0; 
   }
   
   WGraph residualGraph = new WGraph(); 
   residualGraph = updateResidual(graph, flow); 
   
   Path = pathDFS(source, destination, residualGraph); 
   while (!Path.isEmpty()) { 
     flow = augmentPath(Path, residualGraph, flow); 
     residualGraph = updateResidual(graph, flow); 
     Path = pathDFS(source, destination, residualGraph); 
   }
   
   for(int i = 0; i<flow.getNbNodes(); i++) { 
     if(flow.getEdge(source, i) != null) { 
       maxFlow += flow.getEdge(source, i).weight;
       
     }
     
     
   }
   
   for(Edge e : flow.getEdges()) { 
     graph.setEdge(e.nodes[0], e.nodes[1], e.weight); }
   
   
   answer += maxFlow + "\n" + graph.toString(); 
   writeAnswer(filePath+myMcGillID+".txt",answer);
   System.out.println(answer);  }
   
   public static WGraph augmentPath(ArrayList<Integer> Path, WGraph residualGraph, WGraph flow) { 
     int bottleNeck = 1000000000; 
     int temp = 0; 
     int tempweight = 0; 
     for(int i = 0; i<Path.size() -1; i++) {
       temp = residualGraph.getEdge(Path.get(i), Path.get(i+1)).weight; 

   if(temp<bottleNeck) { 
     bottleNeck = temp; } 
   
   }

for(int i = 0; i<Path.size() -1; i++) { 
  if(flow.getEdge(Path.get(i), Path.get(i+1)) != null) { 
    tempweight = flow.getEdge(Path.get(i), Path.get(i+1)).weight; 
    tempweight +=bottleNeck; 
    flow.setEdge(Path.get(i), Path.get(i+1), tempweight); } 
     
     else { 
       tempweight = flow.getEdge(Path.get(i+1), Path.get(i)).weight; 
       tempweight -= bottleNeck; 
       flow.setEdge(Path.get(i+1), Path.get(i), tempweight);  
     
     } 
 }

 return flow; 

}

public static WGraph updateResidual(WGraph graph, WGraph flow) { 
  WGraph residualGraph = new WGraph(); 
  for( Edge e : flow.getEdges()) { 
    
    int capacity = graph.getEdge(e.nodes[0], e.nodes[1]).weight; 
    int flowValue = e.weight; 
    
    if(flowValue < capacity) { 
      if(residualGraph.getEdge(e.nodes[0], e.nodes[1]) == null) { 
        residualGraph.addEdge(new Edge(e.nodes[0], e.nodes[1], (capacity - flowValue))); } 
      
      else { 
        residualGraph.setEdge(e.nodes[0], e.nodes[1], (capacity - flowValue)); 
                              } 

           }
                   

    if(flowValue > 0) { 
      if(residualGraph.getEdge(e.nodes[1], e.nodes[0]) == null) { 
        residualGraph.addEdge(new Edge(e.nodes[1], e.nodes[0], flowValue));
        
      } 
      
      else { 
        residualGraph.setEdge(e.nodes[1], e.nodes[0], flowValue); 
        
      } 
      
    }
  }
    
  
  return residualGraph;  
     
     }
       
   
 
 public static void writeAnswer(String path, String line){
  BufferedReader br = null;
  File file = new File(path);
  // if file doesnt exists, then create it
  
  try {
  if (!file.exists()) {
   file.createNewFile();
  }
  FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
  BufferedWriter bw = new BufferedWriter(fw);
  bw.write(line+"\n"); 
  bw.close();
  } catch (IOException e) {
   e.printStackTrace();
  } finally {
   try {
    if (br != null)br.close();
   } catch (IOException ex) {
    ex.printStackTrace();
   }
  }
 }
 
  public static void main(String[] args){
   String file = args[0];
   File f = new File(file);
   WGraph g = new WGraph(file);
   fordfulkerson(g.getSource(),g.getDestination(),g,f.getAbsolutePath().replace(".txt",""));
  }
}
