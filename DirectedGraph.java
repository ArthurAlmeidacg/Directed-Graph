package pvl7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class DirectedGraph implements PVL7 {

    private List<List<List<Integer>>> edges;
    private List<Integer> nodes;

    public DirectedGraph() {
        edges = new ArrayList<>();
        nodes = new ArrayList<>();
    }

    @Override
    public int newNode() {
        int nodeIndex = nodes.size();
        nodes.add(nodeIndex);
        edges.add(new ArrayList<>());
        return nodeIndex;
    }

    @Override
    public boolean setEdge(int from, int to, int weight) {
        if (from < 0 || from >= nodes.size() || to < 0 || to >= nodes.size()) {
            return false;
        }

        List<List<Integer>> fromEdges = edges.get(from);
        List<Integer> newEdge = new ArrayList<>();
        newEdge.add(to);
        newEdge.add(weight);
        fromEdges.add(newEdge);
        return true;
    }

    @Override
    public List<List<List<Integer>>> getEdges() {
        return edges;
    }

    @Override
    public boolean hasNegativeCircle() {
        int[] distances = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            distances[i] = 0;
        }

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                List<List<Integer>> fromEdges = edges.get(j);
                for (List<Integer> edge : fromEdges) {
                    int to = edge.get(0);
                    int weight = edge.get(1);
                    if (distances[j] + weight < distances[to]) {
                        distances[to] = distances[j] + weight;
                    }
                }
            }
        }

        for (int i = 0; i < nodes.size(); i++) {
            List<List<Integer>> fromEdges = edges.get(i);
            for (List<Integer> edge : fromEdges) {
                int to = edge.get(0);
                int weight = edge.get(1);
                if (distances[i] + weight < distances[to]) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public List<List<Integer>> shortestPath(int source, int destination) {
        int numNodes = nodes.size();

        int[] distances = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        int[] predecessors = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            predecessors[i] = -1;
        }

        distances[source] = 0;

        Set<Integer> visited = new HashSet<>();

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(node -> distances[node]));

        queue.add(source);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            if (visited.contains(currentNode)) {
                continue;
            }

            visited.add(currentNode);

            List<List<Integer>> currentEdges = edges.get(currentNode);
            for (List<Integer> edge : currentEdges) {
                int neighborNode = edge.get(0);
                int weight = edge.get(1);

                int newDistance = distances[currentNode] + weight;

                if (newDistance < distances[neighborNode]) {
                    distances[neighborNode] = newDistance;
                    predecessors[neighborNode] = currentNode;
                    queue.add(neighborNode);
                }
            }
        }

        List<List<Integer>> shortestPath = new ArrayList<>();
        int currentNode = destination;
        while (currentNode != -1) {
            int predecessorNode = predecessors[currentNode];
            if (predecessorNode != -1) {
                List<Integer> edge = new ArrayList<>();
                edge.add(currentNode);
                edge.add(distances[currentNode] - distances[predecessorNode]);
                shortestPath.add(0, edge);
            }
            currentNode = predecessorNode;
        }

        return shortestPath;
    }

    // TODO: Für Bonusaufgabe einkommentieren

    @Override
    public int universalSink() {
        int numNodes = nodes.size();

        for (int candidate = 0; candidate < numNodes; candidate++) {
            boolean isUniversalSink = true;

            for (int source = 0; source < numNodes; source++) {
                if (source != candidate && !canReach(source, candidate)) {
                    isUniversalSink = false;
                    break;
                }
            }

            if (isUniversalSink && hasOutgoingEdges(candidate)) {
                isUniversalSink = false;
            }

            if (isUniversalSink) {
                return candidate;
            }
        }

        return -1;
    }

    private boolean canReach(int source, int target) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(source);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);

            List<List<Integer>> currentEdges = edges.get(current);
            for (List<Integer> edge : currentEdges) {
                int neighbor = edge.get(0);
                if (neighbor == target) {
                    return true;
                }
                stack.push(neighbor);
            }
        }

        return false;
    }

    private boolean hasOutgoingEdges(int node) {
        List<List<Integer>> nodeEdges = edges.get(node);
        return !nodeEdges.isEmpty();
    }

}
