
// package cs2030.simulator;
import java.util.List;

class DoneEvent extends Event {
    DoneEvent(Customer customer, List<Server> servers, int serverID, double eventStartTime) {
        super(customer, servers);
        this.serverID = serverID;
        this.startTime = eventStartTime;
    }

    @Override
    public Event execute() {
        // Get the currentServer from the ServerList using ID
        Server currentServer = ServerList.getServerByID(this.servers, this.serverID);

        /*
         * Update current server on execute.
         * 
         * When DoneEvent executes, the server will be updated from:
         * 
         * - "queueAvailable" to "available"
         * 
         * - "full" to "queueAvailable"
         * 
         * The time also increase
         */
        currentServer = new Server(currentServer.identifier, !currentServer.hasWaitingCustomer, false,
                currentServer.hasWaitingCustomer ? currentServer.nextAvailableTime + 1.0
                        : currentServer.nextAvailableTime);

        // Save updated server back into ServerList
        ServerList.updateServer(this.servers, currentServer.identifier, currentServer);

        // Return null as DoneEvent is the last event in event transitions
        return null;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d done serving by %d", this.startTime, this.customer.customerID, this.serverID);
    }
}
