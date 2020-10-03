
//package cs2030.simulator;
import java.util.List;

class ServeEvent extends Event {
    /**
     * private field to ensure no one else but the internal methods can modify this
     * public getter available for this to give READ only access to external classes
     * static variable, as this is shared across all instances of this class
     */
    private static int numberOfCustomersServed = 0;

    // private final double servingTime;
    private final boolean customerWaited;

    ServeEvent(Customer customer, List<Server> servers, int serverID, double eventStartTime, boolean customerWaited) {
        super(customer, servers);
        this.currentServer = ServerList.getServerByID(servers, serverID);
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

        /*
         * Update current server on execute.
         * 
         * Current server is
         */
        this.currentServer = new Server(this.currentServer.identifier, false, false,
                this.currentServer.nextAvailableTime + 1.0);
        // new Server(this.currentServer.identifier, false, false,
        // this.customer.arrivalTime + 1.0);

        // Save updated server back into ServerList
        ServerList.updateServer(this.servers, this.currentServer.identifier, this.currentServer);

        // Hardcoded 1.0 DoneEvent startTime as serving event takes "1 sec" to execute
        return new DoneEvent(this.customer, this.servers, this.currentServer.identifier, this.startTime + 1.0);
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
