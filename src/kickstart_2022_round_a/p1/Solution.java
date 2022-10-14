package kickstart_2022_round_a.p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        File file = new File("src/kickstart_2022_round_a/p1/sample.txt");
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
            String target = in.next();
            String from = in.next();
            if (solve(target.toCharArray(), from.toCharArray())) {
                System.out.printf("Case #%d: %d\n", i + 1, from.length() - target.length());
            } else {
                System.out.printf("Case #%d: IMPOSSIBLE\n", i + 1);
            }

        }
    }

    public static boolean solve(char[] target, char[] from){
        if (from.length < target.length) {return false;}
        int curIdx = 0;
        for (int i = 0; i < from.length; i++) {
            if (from[i] == target[curIdx]) {
                curIdx++;
            }

            if (curIdx == target.length) {
                return  true;
            }

            if ((target.length - curIdx) > (from.length - i)) {
                return false;
            }
        }

        return false;
    }
}
