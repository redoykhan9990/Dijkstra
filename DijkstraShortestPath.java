package path;
import java.util.*;
public class DijkstraShortestPath {
    static class Edge {
        int target, weight;
        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }
    static class Node implements Comparable<Node> {
        int vertex, dist;
        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node other) {
            return this.dist - other.dist;
        }
    }
    static void dijkstra(List<List<Edge>> graph, int source) {
        int n = graph.size();
        int[] dist = new int[n];
        int[] parent = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[source] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int u = curr.vertex;
            for (Edge edge : graph.get(u)) {
                int v = edge.target;
                int weight = edge.weight;
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }
        System.out.println("Vertex\tDistance\tPath");
        for (int i = 0; i < n; i++) {
            System.out.print(i + "\t" + dist[i] + "\t\t");
            printPath(i, parent);
            System.out.println();
        }
    }
    static void printPath(int vertex, int[] parent) {
        if (vertex == -1) return;
        printPath(parent[vertex], parent);
        System.out.print(vertex + " ");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }
        System.out.println("Enter edges (source destination weight):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();
            graph.get(u).add(new Edge(dest, weight));
        }
        System.out.print("Enter source vertex: ");
        int source = sc.nextInt();
        dijkstra(graph, source);
    }
}
