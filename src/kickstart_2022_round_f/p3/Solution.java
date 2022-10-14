package kickstart_2022_round_f.p3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {
    static class Seed {
        int quantity;
        long days;
        int value;
    }

    public static void main(String[] args) {
        File file = new File("src/kickstart_2022_round_f/p3/sample.txt");
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
            Long d = in.nextLong();
            int n = in.nextInt();
            int x = in.nextInt();

            Seed[] seeds = new Seed[n+1];
            for (int j = 0; j < n; j++) {
                seeds[j] = new Seed();
                seeds[j].quantity = in.nextInt();
                seeds[j].days = in.nextLong();
                seeds[j].value = in.nextInt();
            }
            seeds[n] = new Seed();
            seeds[n].quantity = 1;
            seeds[n].days = d;
            seeds[n].value = 0;

            System.out.printf("Case #%d: %d\n", i + 1, solve(seeds, n, d, x));
        }
    }

    public static long solve(Seed[] seeds, int n, long dayMax, long seedPerDay){
        long res = 0;
        Arrays.sort(seeds, (a,b) -> {
            return Long.compare(a.days, b.days);
        });

        PriorityQueue<Seed> pq = new PriorityQueue<>((a,b) -> {return Integer.compare(b.value, a.value);});

        long today;
        long canPlant;
        long daysToPlant;
        for (int i = 0; i < n; i++) {
            Seed cur = seeds[i];

            if (cur.days == dayMax) {
                break;
            }

            pq.add(cur);
            while(i + 1 < n && seeds[i+1].days == cur.days) {
                pq.add(seeds[i+1]);
                i++;
            }

            today = cur.days;
            daysToPlant = seeds[i+1].days - today;
            canPlant = daysToPlant * seedPerDay;

            while (canPlant > 0 && pq.size() > 0) {
                if (pq.peek().quantity > canPlant) {
                    pq.peek().quantity -= canPlant;
                    res += pq.peek().value * canPlant;
                    break;
                } else {
                    canPlant -= pq.peek().quantity;
                    res += (long)pq.peek().value * pq.peek().quantity;
                    pq.poll();
                }
            }
        }

        return res;
    }
}
