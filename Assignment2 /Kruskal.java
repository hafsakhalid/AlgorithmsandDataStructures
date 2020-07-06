
import java.util.*;

public class Kruskal {

    public static WGraph kruskal(WGraph g){

        /* Fill this method (The statement return null is here only to compile) */
        WGraph mstGraph = new WGraph(); 
        DisjointSets set = new DisjointSets(g.getNbNodes()); 
        //Sort all the edges in the graph in increasing order 
        ArrayList<Edge> sortedEdges = g.listOfEdgesSorted(); 
        for(Edge edge: sortedEdges) { 
            if(IsSafe(set,edge)) { 
                set.union(edge.nodes[0], edge.nodes[1]); 
                mstGraph.addEdge(edge);

            }
        }
        
        return mstGraph;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){
        /* Fill this method (The statement return 0 is here only to compile) */
        boolean flag = true; 
        if(p.find(e.nodes[0]) == p.find(e.nodes[1])) { 
            flag = false;
        }

        return flag;
    
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
