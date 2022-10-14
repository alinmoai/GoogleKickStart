package kickstart_2022_round_f.p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {


    public static void main(String[] args) {
        File file = new File("src/kickstart_2022_round_f/p2/sample.txt");
        System.out.println(file.getAbsolutePath());
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

//        Scanner in = new Scanner(System.in);

        int testCasesNumber = in.nextInt();
        for (int i = 0; i < testCasesNumber; i++){
//            if(i == 11) {
//                int a = 5;
//            }
            int n = in.nextInt();
            int q = in.nextInt();
            List<Integer>[] graph = new List[n + 1];
            for (int j = 0; j < n + 1; j++) {
                graph[j] = new ArrayList<>();
            }

            for (int j = 0; j < n-1; j++) {
                int parent = in.nextInt();
                int child = in.nextInt();
                graph[parent].add(child);
                graph[child].add(parent);
            }

            for (int j = 0; j < q; j++){
                in.nextInt();
            }

            System.out.printf("Case #%d: %d\n", i + 1, solve(graph, n + 1, q));
        }
    }

    public static int solve(List<Integer>[] graph, int n, int water){
        if (water < 1) {return 0;}
        if (water >= n - 1) {return n - 1;}
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> nextQueue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;
        int res = 1;
        int nextLayerCount = 0;
        while(queue.size() > 0) {
            int cur = queue.poll();
            List<Integer> paths = graph[cur];
            for (int path : paths) {
                if (visited[path]) {continue;}
                visited[path] = true;
                nextQueue.add(path);
                nextLayerCount++;
            }

            if (queue.size() == 0) {
                Queue<Integer> temp = queue;
                queue = nextQueue;
                nextQueue = temp;
                if (res + nextLayerCount > water) {
                    return res;
                }
                res += nextLayerCount;
                nextLayerCount = 0;
            }
        }
        return res;
    }
}
