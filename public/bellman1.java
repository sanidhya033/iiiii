import java.util.*;

public class bellman1 {

    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void bellmanFord(int V, List<Edge> edges, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Relax all edges V - 1 times
        for (int i = 1; i < V; i++) 
        {
            for (Edge edge : edges) 
            {
                if (dist[edge.src] != Integer.MAX_VALUE &&
                    dist[edge.src] + edge.weight < dist[edge.dest]) {
                    dist[edge.dest] = dist[edge.src] + edge.weight;
                }
            }
        }

        // Check for negative weight cycles
        for (Edge edge : edges) {
            if (dist[edge.src] != Integer.MAX_VALUE &&
                dist[edge.src] + edge.weight < dist[edge.dest]) {
                System.out.println("Graph contains a negative weight cycle");
                return;
            }
        }

        // Print distances
        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println((char)(i + 'a') + "\t" + 
                (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of vertices:");
        int V = sc.nextInt();

        System.out.println("Enter number of edges:");
        int E = sc.nextInt();

        List<Edge> edges = new ArrayList<>();

        System.out.println("Enter each edge as: src dest weight");
        for (int i = 0; i < E; i++) {
            char sr = sc.next().charAt(0);
            char de = sc.next().charAt(0);
            int weight = sc.nextInt();
            int src = sr - 'a';   
            int dest = de - 'a';
            edges.add(new Edge(src, dest, weight));
        }

        System.out.print("Enter source vertex");
        char s = sc.next().charAt(0);
        int src = s - 'a';

        bellmanFord(V, edges, src);
    }
}
