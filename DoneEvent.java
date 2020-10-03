
// package cs2030.simulator;
import java.util.List;

class DoneEvent extends Event {
    DoneEvent(Customer customer, List<Server> servers, int serverID, double eventStartTime) {
        super(customer, servers);
        this.currentServer = ServerList.getServerByID(servers, serverID);
        this.startTime = eventStartTime;
    }

    @Override
    public Event execute() {
        /*
         * Update current server on execute.
         * 
         * This is the "someone in queue but not served yet" config
         */
        this.currentServer = new Server(this.currentServer.identifier, false, this.currentServer.hasWaitingCustomer,
                !this.currentServer.hasWaitingCustomer ? this.startTime : this.startTime + 1.0);

        // Save updated server back into ServerList
        ServerList.updateServer(this.servers, this.currentServer.identifier, this.currentServer);

        // Return null as DoneEvent is the last event in event transitions
        return null;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d done serving by %d", this.startTime, this.customer.customerID,
                this.currentServer.identifier);
    }
}
