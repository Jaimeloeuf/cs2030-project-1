package simulator;

import java.util.Comparator;

// comparison that returns earlier event startTime as priority
public class Comparison implements Comparator<Event> {
    @Override
    public int compare(Event a, Event b) {

        /* If their time differs */
        if (a.startTime < b.startTime)
            return -1;
        else if (a.startTime > b.startTime)
            return 1;

        /* if they have the same arrival time, order by customerID */
        else if (a.startTime == b.startTime)
            if (a.customer.customerID < b.customer.customerID)
                return -1;
            else
                return 1;

        // This should not happen. Thus throw error if so.
        throw new Error("Invalid events in PriorityQueue for comparison");
    }
}
