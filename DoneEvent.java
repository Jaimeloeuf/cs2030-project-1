
// package cs2030.simulator;
import java.util.List;

class DoneEvent extends Event {
    // @todo Store time when it is done

    // Blank final variables to be assigned in the constructor
    // Made immutable using the "final" modifier
    private final Server currentServer;
    public final double servingTime;

    DoneEvent(Customer customer, List<Server> servers, Server currentServer, double servingTime) {
        super(customer, servers);
        this.currentServer = currentServer;
        this.servingTime = servingTime;
    }

    // Use the execute method to read the time of completion?
    @Override
    public Event execute() {
        // Actually how to see pass this server to the nxt event??

        // @todo Fix the next avail timing
        ServerList.updateServer(this.servers, this.currentServer.identifier, new Server(this.currentServer.identifier,
                !this.currentServer.hasWaitingCustomer, false, this.currentServer.nextAvailableTime));

        return null;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d done serving by %d", servingTime + 1.0, this.customer.customerID,
                this.servers.get(0).identifier);
    }
}
