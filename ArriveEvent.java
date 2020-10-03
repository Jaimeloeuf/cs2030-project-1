
//package cs2030.simulator;
import java.util.List;

class ArriveEvent extends Event {
    // @todo Missing arrived time --> this can be taken from customer's arrivalTime

    ArriveEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.startTime = customer.arrivalTime;
    }

    /**
     * @return New event to be executed after this arrive event
     */
    @Override
    public Event execute() {
        //
        final Server nextAvailableServer = ServerList.getNextAvailableServer(this.servers);
        if (nextAvailableServer != null)
            return new ServeEvent(this.customer, this.servers, nextAvailableServer, this.customer.arrivalTime, false);

        // @todo Should nxt avail time be + 2.0? or jjust a plus 1, ...
        // plus 1
        //
        final Server nextAvailableQueueServer = ServerList.getnextAvailableQueueServer(this.servers);
        if (nextAvailableQueueServer != null)
            return new WaitEvent(this.customer, this.servers, nextAvailableQueueServer, this.customer.arrivalTime);

        //
        return new LeaveEvent(this.customer, this.servers, this.customer.arrivalTime);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d arrives", this.customer.arrivalTime, this.customer.customerID);
    }
}
