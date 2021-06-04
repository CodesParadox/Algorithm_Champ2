



// Algorithm used:

// 1. Select any arbitrary node and run dfs at it. Find the farthest node from selected vertex.

// 2. Run dfs at the farthest node found in step 1. The max distance then returned will be the diameter.

import java.util.ArrayList;

public class Diameter {
    ArrayList<ArrayList<Integer>> graph;

    boolean[] visited;
    int max_dist;
    int best_node;

    public Diameter(boolean[][] adj_matrix)
    {
        int node_count = adj_matrix.length;
        graph = new ArrayList<>();
        for (int i = 0; i < node_count; ++i)
        {
            graph.add(new ArrayList<>());
            for (int j = 0; j < node_count; ++j)
            {
                if (adj_matrix[i][j])
                {
                    graph.get(i).add(j);
                }
            }
        }

    }

    public int get_diam()
    {
        max_dist = 0;
        best_node = 0;
        visited = new boolean[graph.size()];
        // Can replace with forEach
        // IntStream.range(0, visited.length).forEach(i -> visited[i] = false);
        for (int i = 0; i < visited.length; ++i)
            visited[i] = false;

        // best_node will be node which is farthest from 0.
        dfs(0, 0);

        // We will run dfs again from best_node and max_dist will be our answer
        max_dist = 0;
        for (int i = 0; i < visited.length; ++i)
            visited[i] = false;

        dfs(best_node, 0);

        return max_dist;
    }

    void dfs(int node, int l)
    {
        visited[node] = true;

        for (int i = 0; i < graph.get(node).size(); ++i)
            if (!visited[graph.get(node).get(i)])
                dfs(graph.get(node).get(i), l + 1);
        if (l > max_dist)
        {
            max_dist = l;
            best_node = node;
        }
    }
	
	
	// Run time = run time of 2 dfs = 2 * O(V+2*E) = 2* O(V+ 2*(V-1)) ~ O(V)

        /*TEST 1*/
    public static void main(String[] args)
    {
        final long startTime = System.nanoTime(); // Check time at the beginning
        Diameter d;
        boolean[][] mat =
                {
                        {false, true, false, false, false, false},
                        {true, false, true, true, false, false},
                        {false, true, false, false, false, false},
                        {false, true, false, false, true, true},
                        {false, false, false, true, false, false},
                        {false, false, false, true, false, false}
                };


         System.out.println(new Diameter(mat).get_diam());
         final long duration = System.nanoTime() - startTime; // Stop time
        System.out.println(duration); // Print the time in nano

    }

    /*TEST 2*/
//    //public static void main(String[] args) {
//     boolean[][] matrixTest1 = {
//            {false, true, false, false, false, false},
//            {true, false, true, true, false, false},
//            {false, true, false, false, false, false},
//            {false, true, false, false, true, true},
//            {false, false, false, true, false, false},
//            {false, false, false, true, false, false}
//    };
//    Diameter l = new Diameter(matrixTest1);
//    System.out.println("Test 1: " + ((l.get_diam() == 3) ? "Pass." : "Fail."));
//    boolean[][] matrixTest2 = {
//            {false, false, false, true, false, false, true, false, false, false, false, false},
//            {false, false, false, false, false, false, false, false, false, true, false, false},
//            {false, false, false, false, false, false, false, false, true, false, false, true},
//            {true, false, false, false, false, false, false, false, false, false, false, false},
//            {false, false, false, false, false, true, false, false, true, true, false, false},
//            {false, false, false, false, true, false, true, true, false, false, false, false},
//            {true, false, false, false, false, true, false, false, false, false, false, false},
//            {false, false, false, false, false, true, false, false, false, false, false, false},
//            {false, false, true, false, true, false, false, false, false, false, true, false},
//            {false, true, false, false, true, false, false, false, false, false, false, false},
//            {false, false, false, false, false, false, false, false, true, false, false, false},
//            {false, false, true, false, false, false, false, false, false, false, false, false}};
//    Diameter d = new Diameter(matrixTest2);
//    System.out.println("Test 2: " + ((d.get_diam() == 7) ? "Pass." : "Fail."));
//    boolean[][] matrixTest3 = {{false, true, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false},
//            {true, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false},
//            {false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, false, false},
//            {false, false, false, false, true, false, false, true, false, false, false, false, false, false, false, false, false},
//            {false, false, true, true, false, true, false, false, false, false, false, false, false, false, false, false, false},
//            {false, true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false},
//            {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
//            {false, false, false, true, false, false, false, false, false, false, false, false, true, true, false, false, false},
//            {false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false},
//            {false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
//            {false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false},
//            {false, true, false, false, false, false, false, false, true, false, true, false, false, false, false, false, false},
//            {false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false},
//            {false, false, false, false, false, false, false, true, false, false, false, false, false, false, true, false, false},
//            {false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, true, false},
//            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, true},
//            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false}};
//    Diameter p = new Diameter(matrixTest3);
//    System.out.println("Test 3: " + ((p.get_diam() == 10) ? "Pass." : "Fail."));
//}
}


