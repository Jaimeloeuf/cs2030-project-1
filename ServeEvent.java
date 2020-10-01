
//package cs2030.simulator;
import java.util.List;

class ServeEvent extends Event {
    // @todo Missing serve time

    private final boolean noWaitingTime;

    ServeEvent(Customer customer, List<Server> server, boolean noWaitingTime) {
        super(customer, server);
        this.noWaitingTime = noWaitingTime;
    }

    public double servingTime() {
        return noWaitingTime ? super.customer.arrivalTime : super.servers.get(0).nextAvailableTime;
    }

    @Override
    public Event execute() {
        return new DoneEvent(super.customer, super.servers, this.servingTime());
    }

    @Override
    public String toString() {
        return String.format("%.3f %d served by %d", this.servingTime(), super.customer.customerID,
                super.servers.get(0).identifier);
    }

}
