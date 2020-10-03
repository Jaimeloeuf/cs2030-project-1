
// package cs2030.simulator;
import java.util.List;

class LeaveEvent extends Event {
    /**
     * private field to ensure no one else but the internal methods can modify this
     * public getter available for this to give READ only access to external classes
     * static variable, as this is shared across all instances of this class
     */
    private static int numberOfCustomersLeftWithoutService = 0;

    LeaveEvent(Customer customer, List<Server> servers, double eventStartTime) {
        super(customer, servers);
        this.startTime = eventStartTime;
    }

    /**
     * Only update the "number of customers left without service" on execute
     */
    @Override
    public Event execute() {
        // Increment "numberOfCustomersLeftWithoutService" tracking how many left
        ++numberOfCustomersLeftWithoutService;
        return null;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d leaves", this.startTime, this.customer.customerID);
    }

    // "numberOfCustomersLeftWithoutService" static variable Getter for statistics
    public static int getNumberOfCustomersLeftWithoutService() {
        return numberOfCustomersLeftWithoutService;
    }
}
