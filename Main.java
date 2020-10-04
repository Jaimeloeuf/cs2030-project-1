import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;

class Main {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);

        /**
         * So there is a pointer/reference, pointing to the List of servers.
         * 
         * Everytime I create a new ArriveEvent and pass it the List of servers, I am
         * passing its pointer BY VALUE to the new event object. However since the
         * parameter passed is actually a pointer, modifying/replacing Server Objects in
         * the List will actually still modify the original Servers List.
         * 
         * This means that, EVERY SINGLE EVENT object will ALWAYS have a reference to
         * the same Servers List, and any of them modifying any Server Object in the
         * List, modify the objects in the List.
         * 
         * Thus, EVERY OTHER EVENT, can access the updated Server Objects in the List.
         * Specifically through the "ServerList.getServerByID" static method.
         */

        List<Server> servers = new ArrayList<Server>();

        // List to store all the events as strings to output after simulation
        List<String> schedule = new ArrayList<String>();

        // @todo Remove hardcoded initial capacity
        // 11 following the default value of the class
        PriorityQueue<Event> queue = new PriorityQueue<Event>(11, new Comparison());

        // Create list of servers at the start using the first user input
        // Server ID starts from 1
        for (int i = 1, numOfServers = sc.nextInt(); i <= numOfServers; i++)
            servers.add(new Server(i, true, false, 0));

        // Customer ID starts from 1
        // Read user input 1 by 1, and create a customer object, before
        // Inserting an Arrive event into Queue for every customer
        for (int customerID = 1; sc.hasNextDouble(); customerID++)
            queue.add(new ArriveEvent(new Customer(customerID, sc.nextDouble()), servers));

        // Explicitly close scanner to prevent resource leak
        sc.close();

        // Loop to simulate running through all the events.
        while (!queue.isEmpty()) {
            final Event event = queue.poll();

            // Only for DoneEvent and LeaveEvent, where their execute methods are not
            // expected to return anymore new events, do we skip adding new events.
            if (event instanceof DoneEvent || event instanceof LeaveEvent)
                event.execute();
            else
                queue.add(event.execute());

            // Add the string representation of each event after execution into the schedule
            // List to be displayed after simulation completes.
            schedule.add(event.toString());
        }

        // Sort schedule by execution time (the first number in each string)
        Collections.sort(schedule);

        // Print the string representation of the entire simulation
        for (String schedules : schedule)
            System.out.println(schedules);

        // Get and print the statistics of the simulation.
        getStatistics();
    }

    public static void getStatistics() {
        // Statistics 1, "the average waiting time for customers who have been served"
        final double stat1 = ServeEvent.getTotalWaitingTime() / ServeEvent.getNumberOfCustomersServed();

        // Statistics 2, "the number of customers served"
        final int stat2 = ServeEvent.getNumberOfCustomersServed();

        // Statistics 3, "the number of customers who left without being served"
        final int stat3 = LeaveEvent.getNumberOfCustomersLeftWithoutService();

        System.out.printf("[%.3f %d %d]\n", stat1, stat2, stat3);
    }
}
