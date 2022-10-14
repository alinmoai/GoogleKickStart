package kickstart_2022_round_a.p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        File file = new File("src/kickstart_2022_round_a/p2/sample.txt");
        System.out.println(file.getAbsolutePath());
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        File ans = new File("src/kickstart_2022_round_a/p2/ts1_output.txt");
        Scanner out = null;
        try {
            out = new Scanner(ans);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
//        Scanner in = new Scanner(System.in);

        int testCasesNumber = in.nextInt();
        for (int i = 0; i < testCasesNumber; i++){
            String num = in.next();
            System.out.printf("Case #%d: %s\n", i + 1, solve(num));
            System.out.println(out.nextLine());
        }
    }

    public static String solve(String num){
        StringBuilder sb = new StringBuilder(num);
        char c[] = num.toCharArray();
        int sum = 0;
        for (int i = 0; i < c.length; i++) {
            sum += c[i] - '0';
        }

        int ans = (9 - (sum % 9)) % 9;

        if (c[0] == '0') {
            return ans + num;
        }

        if (ans == 0) {
            sb.insert(1, '0');
            return sb.toString();
        }

        for (int i = 0; i < c.length; i++) {
            if (c[i] - '0' > ans) {
                sb.insert(i, (char) (ans + '0'));
                return sb.toString();
            }
        }

        sb.append((char) (ans + '0'));

        return sb.toString();
    }
}
