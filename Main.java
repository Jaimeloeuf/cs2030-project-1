import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;

class Main {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);

        //
        List<Server> servers = new ArrayList<Server>();
        List<Customer> customers = new ArrayList<Customer>();
        List<String> schedule = new ArrayList<String>();

        final int numOfServers = sc.nextInt();

        // Customer ID starts from 1
        int customerID = 1;

        // Create the list of servers at the start using the first user input
        // Server ID starts from 1
        for (int i = 1; i <= numOfServers; i++)
            // WRONG --> Server starts with being available with no one waiting and start
            // time of 1.0
            servers.add(new Server(i, true, false, 0));

        // Read user input 1 by 1, and create+store the customer objects
        while (sc.hasNextDouble()) {
            // Create customer with incrementing customer ID
            customers.add(new Customer(customerID++, sc.nextDouble()));
            System.out.println(customers.toString());
        }

        // Explicitly close scanner to prevent resource leak
        sc.close();

        // @todo Remove hardcoded initial capacity
        // 11 following the default value of the class
        PriorityQueue<Event> queue = new PriorityQueue<Event>(11, new Comparison());

        /**
         * So there is a pointer/reference, pointing to the ArrayList of servers.
         * 
         * Everytime I create a new ArriveEvent and pass it the ArrayList of servers, I
         * am passing its pointer BY VALUE to the new event object. However since the
         * parameter passed is actually a pointer, modifying/replacing Server Objects in
         * the ArrayList will actually still modify the original Servers ArrayList.
         * 
         * This means that, EVERY SINGLE EVENT object will ALWAYS have a reference to
         * the same Servers ArrayList, and any of them modifying any Server Object in
         * the List modify the objects in the ArrayList defined HERE.
         * 
         * Thus, EVERY OTHER EVENT, will also be able to access the updated Server
         * Objects in the ArrayList
         */

        // Insert Arrive event for all the customers into the Queue
        for (int i = 0; i < customers.size(); i++)
            queue.add(new ArriveEvent(customers.get(i), servers));

        // Loop to simulate running through all the events.
        while (!queue.isEmpty()) {
            final Event event = queue.poll();
            System.out.printf("event: %s %s\n", event.getClass().toString(), event.toString());
            System.out.printf("servers status before execute: %s\n", servers.toString());

            // Only for DoneEvent and LeaveEvent, where their execute methods are not
            // expected to return anymore new events, do we skip adding new events.
            if (event instanceof DoneEvent || event instanceof LeaveEvent)
                event.execute();
            else
                queue.add(event.execute());

            System.out.printf("servers status after execute: %s\n", servers.toString());

            //
            schedule.add(event.toString());
        }

        // Sort schedule by execution time
        // Wait what how does this sort it by that...
        Collections.sort(schedule);
        for (String schedules : schedule)
            System.out.println(schedules);

        getStatistics();
    }

    public static void getStatistics() {
        // Statistics 1, "the average waiting time for customers who have been served"
        final double stat1 = ServeEvent.getNumberOfCustomersServed();

        // Statistics 2, "the number of customers served"
        final int stat2 = ServeEvent.getNumberOfCustomersServed();

        // Statistics 3, "the number of customers who left without being served"
        final int stat3 = LeaveEvent.getNumberOfCustomersLeftWithoutService();

        System.out.printf("[%.3f %d %d]\n", stat1, stat2, stat3);
    }
}

// comparison that returns earlier event startTime as priority
class Comparison implements Comparator<Event> {
    @Override
    public int compare(Event a, Event b) {

        // If their time differs
        if (a.startTime < b.startTime)
            return -1;
        else if (a.startTime > b.startTime)
            return 1;

        // if they have the same arrival time, check the customerID
        else if (a.startTime == b.startTime)
            if (a.customer.customerID < b.customer.customerID)
                return -1;
            else
                return 1;

        else
            return 0;
    }
}
