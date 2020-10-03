
// package cs2030.simulator;
import java.util.List;

class WaitEvent extends Event {
    WaitEvent(Customer customer, List<Server> servers, int serverID, double eventStartTime) {
        super(customer, servers);
        this.currentServer = ServerList.getServerByID(servers, serverID);
        this.startTime = eventStartTime;
    }

    /**
     * This event's execute always turns current server from queueAvailable to full
     */
    @Override
    public Event execute() {
        /*
         * Update current server on execute.
         * 
         * Current server IS UNAVAILABLE with a waiting queue, for waitEvent to be
         * returned by ArriveEvent's execute.
         * 
         * Thus this Waiting execute updates server to "hasWaitingCustomer"
         *
         * "nextAvailableTime" is not updated, since it should stay the same, just that
         * now, instead of being next available at X, it is now available to serve this
         * waiting customer at X.
         */
        this.currentServer = new Server(this.currentServer.identifier, false, true,
                this.currentServer.nextAvailableTime);

        // Save updated server back into ServerList
        ServerList.updateServer(this.servers, this.currentServer.identifier, this.currentServer);

        return new ServeEvent(this.customer, this.servers, this.currentServer.identifier,
                this.currentServer.nextAvailableTime, true);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d waits to be served by %d", this.customer.arrivalTime, this.customer.customerID,
                this.currentServer.identifier);
    }
}
