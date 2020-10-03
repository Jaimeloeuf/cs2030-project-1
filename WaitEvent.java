
// package cs2030.simulator;
import java.util.List;

class WaitEvent extends Event {
    // @todo Missing wait time

    private final Server currentServer;

    WaitEvent(Customer customer, List<Server> servers, Server currentServer, double eventStartTime) {
        super(customer, servers);
        this.currentServer = currentServer;
        this.startTime = eventStartTime;
    }

    @Override
    public Event execute() {
        // need to change next avail timing?
        // Technically can hard code false for isAvailable
        ServerList.updateServer(this.servers, this.currentServer.identifier, new Server(this.currentServer.identifier,
                this.currentServer.isAvailable, true, this.currentServer.nextAvailableTime));

        // @todo why this the nxt availb time is a arrivalTime + 1.0??
        return new ServeEvent(this.customer, this.servers, currentServer, this.currentServer.nextAvailableTime, true);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d waits to be served by %d", this.customer.arrivalTime, this.customer.customerID,
                this.currentServer.identifier);
    }
}
