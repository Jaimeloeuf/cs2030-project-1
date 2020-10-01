
// package cs2030.simulator;
import java.util.List;

class DoneEvent extends Event {
    // @todo Store time when it is done

    // Blank final variables to be assigned in the constructor
    // Made immutable using the "final" modifier
    private final double servingTime;

    DoneEvent(Customer customer, List<Server> server, double servingTime) {
        super(customer, server);
        this.servingTime = servingTime;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d done serving by %d", servingTime + 1.0, super.customer.customerID,
                super.servers.get(0).identifier);
    }
}
