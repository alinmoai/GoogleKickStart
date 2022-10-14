package kickstart_2022_round_f.p4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
//        File file = new File("src/p4/sample.txt");
        File file = new File("src/kickstart_2022_round_f/p4/sample.txt");
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
            int k = in.nextInt();
            int x = in.nextInt();
            int d = in.nextInt();
            int m = in.nextInt();

            int[][] meetings = new int[m][3];
            for (int metting = 0; metting < m; metting++){
                meetings[metting][0] = in.nextInt();
                meetings[metting][1] = in.nextInt();
                meetings[metting][2] = in.nextInt();
            }
            System.out.printf("Case #%d: %d\n", i + 1, solve(meetings, n, k, x, d));
        }
    }

    static class LeadEvent{
        int leadId;
        int time;

        public LeadEvent(int id, int t) {
            leadId = id;
            time = t;
        }

        @Override
        public String toString() {
            return "time: " + time + ", " + ", leadId: " + leadId;
        }
    }

    public static int solve(int[][] meetings, int leadCount, int leadToMeet, int meetingPeriod, int endTime){
        int m = meetings.length;
        LeadEvent[] eventsAdd = new LeadEvent[m];
        LeadEvent[] eventsRemove = new LeadEvent[m];

        for (int i = 0; i < meetings.length; i++) {
            int[] meeting = meetings[i];
            eventsAdd[i] = new LeadEvent(meeting[0] - 1, meeting[1]);
            eventsRemove[i] = new LeadEvent(meeting[0] - 1, meeting[2]);
        }

        Arrays.sort(eventsAdd, (a, b) -> Integer.compare(a.time, b.time));
        Arrays.sort(eventsRemove, (a, b) -> Integer.compare(a.time, b.time));

        int[] leads = new int[leadCount];
        int res = m;
        int curCancel = 0;
        int[] countCount = new int[m+1];
        int[] selectedCount = new int[m+1];
        countCount[0] = leadCount;
        selectedCount[0] = leadToMeet;

        int curAdd = 0;
        int curRemove = 0;
        for (int start = 0; start <= endTime - meetingPeriod; start++) {
            int end = start + meetingPeriod;
            while (curAdd < m && eventsAdd[curAdd].time < end) {
                LeadEvent curE = eventsAdd[curAdd];
                curAdd++;

                int oriCount = leads[curE.leadId];
                countCount[leads[curE.leadId]]--;
                leads[curE.leadId]++;
                countCount[leads[curE.leadId]]++;

                if(selectedCount[oriCount] > countCount[oriCount]) {
                    selectedCount[oriCount]--;
                    selectedCount[oriCount+1]++;
                    curCancel++;
                }
            }

            while (curRemove < m && eventsRemove[curRemove].time <= start) {
                LeadEvent curE = eventsRemove[curRemove];
                curRemove++;

                int oriCount = leads[curE.leadId];
                countCount[leads[curE.leadId]]--;
                leads[curE.leadId]--;
                countCount[leads[curE.leadId]]++;

                if(selectedCount[oriCount] > 0) {
                    selectedCount[oriCount]--;
                    selectedCount[oriCount-1]++;
                    curCancel--;
                }
            }

            res = Math.min(res, curCancel);
        }
        return res;
    }
}