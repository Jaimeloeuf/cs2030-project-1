
//package cs2030.simulator;
import java.util.List;

class ServeEvent extends Event {
    /**
     * private field to ensure no one else but the internal methods can modify this
     * public getter available for this to give READ only access to external classes
     * static variable, as this is shared across all instances of this class
     */
    private static int numberOfCustomersServed = 0;

    // @todo Missing serve time

    private final double servingTime;

    ServeEvent(Customer customer, List<Server> server, boolean customerWaited) {
        super(customer, server);
        // @todo Remove the hard coded server 0
        this.servingTime = customerWaited ? this.customer.arrivalTime : this.servers.get(0).nextAvailableTime;
    }

    @Override
    public Event execute() {
        // Increment the numberOfCustomersServed once a serve event is executed
        ++numberOfCustomersServed;

        //
        this.servers.set(0, new Server(this.servers.get(0).identifier, false, this.servers.get(0).hasWaitingCustomer,
                event.customer.arrivalTime + 1.0));

        return new DoneEvent(this.customer, this.servers, this.servingTime);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d served by %d", this.servingTime, this.customer.customerID,
                this.servers.get(0).identifier);
    }

    // "numberOfCustomersServed" static variable Getter for statistics needed
    public static int getNumberOfCustomersServed() {
        return numberOfCustomersServed;
    }
}
