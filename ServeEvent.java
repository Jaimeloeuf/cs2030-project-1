
//package cs2030.simulator;
import java.util.List;

class ServeEvent extends Event {
    /**
     * private field to ensure no one else but the internal methods can modify this
     * public getter available for this to give READ only access to external classes
     * static variable, as this is shared across all instances of this class
     */
    private static int numberOfCustomersServed = 0;

    ServeEvent(Customer customer, List<Server> servers, int serverID, double eventStartTime) {
        super(customer, servers);
        this.serverID = serverID;
        this.startTime = eventStartTime;
    }

    @Override
    public Event execute() {
        // Increment the numberOfCustomersServed once a serve event is executed
        ++numberOfCustomersServed;

        // Get the currentServer from the ServerList using ID
        Server currentServer = ServerList.getServerByID(this.servers, this.serverID);

        /*
         * Update current server on execute.
         * 
         * The only events that can execute in sequence before this event, is
         * ArriveEvent and DoneEvent, EVEN THOUGH the Event subclasses execute methods
         * that can INSTANTIATE AND RETURN ServeEvent is ArriveEvent and WaitEvent.
         * 
         * By chaining, I mean the flow of execution. So which event executed right
         * before this event.
         * 
         * If chained from ArriveEvent, the status of the current server will be
         * "available", then this method will update the server's status to
         * "queueAvailable", by setting "isAvailable" to true.
         * 
         * If chained from DoneEvent, the status of the current server will be
         * "queueAvailable", then this method WILL NOT UPDATE the server's status, since
         * it is already all set as it should be.'
         */
        currentServer = new Server(currentServer.identifier, false, false, this.startTime + 1.0);

        // Save updated server back into ServerList
        ServerList.updateServer(this.servers, currentServer.identifier, currentServer);

        // Hardcoded 1.0 DoneEvent startTime as serving event takes "1 sec" to execute
        return new DoneEvent(this.customer, this.servers, this.serverID, this.startTime + 1.0);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d served by %d", this.startTime, this.customer.customerID, this.serverID);
    }

    // "numberOfCustomersServed" static variable Getter for statistics needed
    public static int getNumberOfCustomersServed() {
        return numberOfCustomersServed;
    }
}
