
// package cs2030.simulator;
import java.util.List;

class LeaveEvent extends Event {
    // @todo Missing leave time

    LeaveEvent(Customer customer, List<Server> server) {
        super(customer, server);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d leaves", this.customer.arrivalTime, this.customer.customerID);
    }
}
