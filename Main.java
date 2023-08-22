package pvl7;

public class Main {
    public static void main(String[] args) throws Exception {
        // PVL7 graph_a = new DirectedGraph();
        // System.out.println(graph_a.newNode()); // 0
        // System.out.println(graph_a.newNode()); // 1
        // System.out.println(graph_a.newNode()); // 2
        // System.out.println(graph_a.newNode()); // 3
        // System.out.println(graph_a.setEdge(0, 1, 1)); // true
        // System.out.println(graph_a.setEdge(1, 2, 2)); // true
        // System.out.println(graph_a.setEdge(2, 0, -4)); // true
        // System.out.println(graph_a.setEdge(2, 3, 6)); // true
        // System.out.println(graph_a.getEdges());
        // // [
        // // [[1,1]],
        // // [[2,2]],
        // // [[0,-4], [3,6]],
        // // []
        // // ]
        // System.out.println(graph_a.hasNegativeCircle()); // true
        // System.out.println(graph_a.shortestPath(0, 2)); // []

        PVL7 graph_b = new DirectedGraph();

        System.out.println(graph_b.newNode()); // 0
        System.out.println(graph_b.newNode()); // 1
        System.out.println(graph_b.newNode()); // 2
        System.out.println(graph_b.newNode()); // 3
        System.out.println(graph_b.newNode()); // 4
        System.out.println(graph_b.newNode()); // 5
        System.out.println(graph_b.setEdge(0, 1, 8));
        System.out.println(graph_b.setEdge(0, 2, 1));
        System.out.println(graph_b.setEdge(1, 2, 1));
        System.out.println(graph_b.setEdge(1, 3, 1));
        System.out.println(graph_b.setEdge(2, 4, 1));
        System.out.println(graph_b.setEdge(3, 1, 1));
        System.out.println(graph_b.setEdge(3, 5, 1));
        System.out.println(graph_b.setEdge(4, 3, 1));
        System.out.println(graph_b.setEdge(4, 5, 8));
        System.out.println(graph_b.hasNegativeCircle()); // false
        System.out.println(graph_b.shortestPath(0, 5)); // [[2,1], [4,1], [3,1], [5,1]]

        System.out.println(graph_b.universalSink()); // 5
    }
}
