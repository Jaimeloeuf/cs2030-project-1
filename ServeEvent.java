
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

    private final Server currentServer;
    // private final double servingTime;
    private final boolean customerWaited;

    // Should have 1 more parameter for the ID of the server for all events right,
    // accept the first arriveEvent
    ServeEvent(Customer customer, List<Server> servers, Server currentServer, double eventStartTime,
            boolean customerWaited) {
        super(customer, servers);
        this.currentServer = currentServer;
        this.startTime = eventStartTime;

        // @todo Remove the hard coded server 0
        // @todo fix serving time
        this.customerWaited = customerWaited;
        // hard code 1.0 additon since each cycle is 1.0 seconds
        // this.servingTime = customerWaited ? this.customer.arrivalTime + 1.0 :
        // this.customer.arrivalTime;
    }

    @Override
    public Event execute() {
        // Increment the numberOfCustomersServed once a serve event is executed
        ++numberOfCustomersServed;

        ServerList.updateServer(this.servers, this.currentServer.identifier,
                new Server(this.currentServer.identifier, false, this.currentServer.hasWaitingCustomer,
                        !this.currentServer.hasWaitingCustomer ? this.startTime + 1.0 : this.startTime + 2.0));
        // this.startTime + 1.0));

        // Hardcoded 1.0 as each serving event takes "1 sec" to execute
        return new DoneEvent(this.customer, this.servers, this.currentServer, this.startTime + 1.0);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d served by %d", this.startTime, this.customer.customerID,
                this.currentServer.identifier);
    }

    // "numberOfCustomersServed" static variable Getter for statistics needed
    public static int getNumberOfCustomersServed() {
        return numberOfCustomersServed;
    }
}
