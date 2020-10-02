
//package cs2030.simulator;
import java.util.List;

class ArriveEvent extends Event {
    // @todo Missing arrived time --> this can be taken from customer's arrivalTime

    ArriveEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
    }

    /**
     * @return New event to be executed after this arrive event
     */
    @Override
    public Event execute() {
        final Server nextAvailableServer = ServerList.getNextAvailableServer(this.servers);
        if (nextAvailableServer != null)
            return new ServeEvent(this.customer,
                    ServerList
                            .updateServer(this.servers, nextAvailableServer.identifier,
                                    new Server(nextAvailableServer.identifier, false,
                                            nextAvailableServer.hasWaitingCustomer, this.customer.arrivalTime + 1.0)),
                    nextAvailableServer, true);

        // @todo Should nxt avail time be + 2.0? or jjust a plus 1, ...
        // plus 1
        final Server nextAvailableQueueServer = ServerList.getnextAvailableQueueServer(this.servers);
        if (nextAvailableQueueServer != null)
            return new WaitEvent(this.customer,
                    ServerList.updateServer(this.servers, nextAvailableQueueServer.identifier,
                            new Server(nextAvailableQueueServer.identifier, nextAvailableQueueServer.isAvailable, true,
                                    // need to change next avail timing?
                                    nextAvailableQueueServer.nextAvailableTime + 1.0)),
                    nextAvailableQueueServer);

        return new LeaveEvent(this.customer, this.servers);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d arrives", this.customer.arrivalTime, this.customer.customerID);
    }
}
