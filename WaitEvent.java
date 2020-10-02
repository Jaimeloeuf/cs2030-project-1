
// package cs2030.simulator;
import java.util.List;

class WaitEvent extends Event {
    // @todo Missing wait time

    private final Server currentServer;

    WaitEvent(Customer customer, List<Server> servers, Server currentServer) {
        super(customer, servers);
        this.currentServer = currentServer;
    }

    @Override
    public Event execute() {
        // need to change next avail timing?
        ServerList.updateServer(this.servers, this.currentServer.identifier, new Server(this.currentServer.identifier,
                this.currentServer.isAvailable, true, this.currentServer.nextAvailableTime + 1.0));

        return new ServeEvent(this.customer, this.servers, currentServer, true);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d waits to be served by %d", this.customer.arrivalTime, this.customer.customerID,
                this.servers.get(0).identifier);
    }
}
