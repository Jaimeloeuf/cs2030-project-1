
// package cs2030.simulator;
import java.util.List;

class WaitEvent extends Event {
    WaitEvent(Customer customer, List<Server> servers, int serverID, double eventStartTime) {
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
         * Current server IS UNAVAILABLE with a waiting queue, for waitEvent to be
         * returned by ArriveEvent's execute. Meaning status of current server is always
         * "queueAvailable", and this execute method will update its status to "full",
         * by setting "hasWaitingCustomer" to true on the current server.
         * 
         * "nextAvailableTime" is not updated, since it should stay the same, just that
         * now, instead of being next available at X, it is now available to serve this
         * waiting customer at X.
         */
        // currentServer = new Server(currentServer.identifier, false, true,
        // currentServer.nextAvailableTime);
        currentServer = new Server(currentServer.identifier, currentServer.isAvailable, true,
                currentServer.nextAvailableTime);

        // Save updated server back into ServerList
        ServerList.updateServer(this.servers, currentServer.identifier, currentServer);

        return new ServeEvent(this.customer, this.servers, this.serverID, currentServer.nextAvailableTime, true);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d waits to be served by %d", this.startTime, this.customer.customerID,
                this.serverID);
    }
}
