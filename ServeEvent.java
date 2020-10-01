
//package cs2030.simulator;
import java.util.List;

class ServeEvent extends Event {
    // @todo Missing serve time

    private final boolean noWaitingTime;

    /**
     * private field to ensure no one else but the internal methods can modify this
     * public getter available for this to give READ only access to external classes
     * static variable, as this is shared across all instances of this class
     */
    private static int numberOfCustomersServed = 0;

    ServeEvent(Customer customer, List<Server> server, boolean noWaitingTime) {
        super(customer, server);
        this.noWaitingTime = noWaitingTime;
    }

    public double servingTime() {
        return noWaitingTime ? super.customer.arrivalTime : super.servers.get(0).nextAvailableTime;
    }

    @Override
    public Event execute() {
        // Increment the numberOfCustomersServed once a serve event is executed
        ++numberOfCustomersServed;

        return new DoneEvent(super.customer, super.servers, this.servingTime());
    }

    @Override
    public String toString() {
        return String.format("%.3f %d served by %d", this.servingTime(), super.customer.customerID,
                super.servers.get(0).identifier);
    }

    // "numberOfCustomersServed" static variable Getter for statistics needed
    public static int getNumberOfCustomersServed() {
        return numberOfCustomersServed;
    }
}
