
// package cs2030.simulator;
import java.util.List;

class WaitEvent extends Event {
    // @todo Missing wait time

    WaitEvent(Customer customer, List<Server> server) {
        super(customer, server);
    }

    @Override
    public Event execute() {
        return new ServeEvent(this.customer, this.servers, false);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d waits to be served by %d", this.customer.arrivalTime, this.customer.customerID,
                this.servers.get(0).identifier);
    }
}
