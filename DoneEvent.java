
// package cs2030.simulator;
import java.util.List;

class DoneEvent extends Event {
    // @todo Store time when it is done

    // Blank final variables to be assigned in the constructor
    // Made immutable using the "final" modifier
    private final Server currentServer;

    DoneEvent(Customer customer, List<Server> servers, Server currentServer, double eventStartTime) {
        super(customer, servers);
        this.currentServer = currentServer;
        this.startTime = eventStartTime;
    }

    @Override
    public Event execute() {

        System.out.printf("%s %s %.3f\n", this.currentServer.status(), this.currentServer.hasWaitingCustomer,
                this.currentServer.nextAvailableTime);

        // @todo Fix the next avail timing
        // Actually how to see pass this server to the nxt event??
        // WHen a done event is executed, the server should be updated
        // from full to queueAvailable
        // from queueAvailable to available
        ServerList.updateServer(this.servers, this.currentServer.identifier,
                new Server(this.currentServer.identifier, !this.currentServer.hasWaitingCustomer, false,
                        !this.currentServer.hasWaitingCustomer ? this.startTime : this.startTime + 1.0));

        System.out.printf("%s\n", this.currentServer.status());

        return null;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d done serving by %d", this.startTime, this.customer.customerID,
                this.currentServer.identifier);
    }
}
