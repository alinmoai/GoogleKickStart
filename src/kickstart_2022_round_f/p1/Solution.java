package kickstart_2022_round_f.p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    static class Fabric {
        String color;
        int durability;
        int id;
    }

    public static void main(String[] args) {
        File file = new File("src/kickstart_2022_round_f/p1/sample.txt");
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
            int n = in.nextInt();
            Fabric[] fabrics = new Fabric[n];

            for (int j = 0; j < n; j++){
                fabrics[j] = new Fabric();
                fabrics[j].color = in.next();
                fabrics[j].durability = in.nextInt();
                fabrics[j].id = in.nextInt();
            }
            System.out.printf("Case #%d: %d\n", i + 1, solve(fabrics, n));
        }
    }

    public static int solve(Fabric[] fabrics, int n){
        Integer[] adaIndex = new Integer[n];
        Integer[] charlesIndex = new Integer[n];
        for (int i = 0; i < n; i++) {
            adaIndex[i] = i;
            charlesIndex[i] = i;
        }

        Arrays.sort(adaIndex, (a,b) -> {
            int compare = fabrics[a].color.compareTo(fabrics[b].color);
            if (compare == 0) {
                return Integer.compare(fabrics[a].id, fabrics[b].id);
            }
            return compare;
        });

        Arrays.sort(charlesIndex, (a,b) -> {
            if (fabrics[a].durability == fabrics[b].durability) {
                return Integer.compare(fabrics[a].id, fabrics[b].id);
            }
            return Integer.compare(fabrics[a].durability, fabrics[b].durability);
        });

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (adaIndex[i].equals(charlesIndex[i])) {
                res++;
            }
        }
        return res;
    }
}
