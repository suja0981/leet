import java.util.*;

class Solution {
    public int maxEvents(int[][] events) {
        // Step 1: Sort events by startDay
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));

        // Min-heap to store end days
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int i = 0, n = events.length;
        int day = 1, count = 0;

        // Find the last day in all events
        int lastDay = 0;
        for (int[] e : events) lastDay = Math.max(lastDay, e[1]);

        // Step 2: Loop from day 1 to lastDay
        while (day <= lastDay) {
            // Add all events that start today
            while (i < n && events[i][0] == day) {
                pq.add(events[i][1]);
                i++;
            }

            // Remove expired events
            while (!pq.isEmpty() && pq.peek() < day) {
                pq.poll();
            }

            // Attend the event that ends the earliest
            if (!pq.isEmpty()) {
                pq.poll();
                count++;
            }

            day++;
        }

        return count;
    }
}
