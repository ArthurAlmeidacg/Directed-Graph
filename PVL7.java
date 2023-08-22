package pvl7;


import java.util.List;

public interface PVL7 {
    int newNode();
    boolean setEdge(int from, int to, int weight);
    List<List<List<Integer>>> getEdges();
    boolean hasNegativeCircle();
    List<List<Integer>> shortestPath(int source, int destination);

    //TODO: Für Bonusaufgabe einkommentieren
    
    int universalSink();

    
}
