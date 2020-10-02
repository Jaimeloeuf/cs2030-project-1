
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
        return new ServeEvent(this.customer,
                ServerList.updateServer(this.servers, currentServer.identifier,
                        new Server(this.currentServer.identifier,
                                // @todo Dunnid to udpate nxt time right, since the arrive event alr added to it
                                false, false, this.currentServer.nextAvailableTime)),
                currentServer, true);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d waits to be served by %d", this.customer.arrivalTime, this.customer.customerID,
                this.servers.get(0).identifier);
    }
}
