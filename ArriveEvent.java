
//package cs2030.simulator;
import java.util.List;

class ArriveEvent extends Event {
    ArriveEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.startTime = customer.arrivalTime;
    }

    /**
     * The startTime of the next event, be it Serve OR Wait OR Leave, will always be
     * the same as this event's startTime. Since the next event should be executed
     * right after this event is executed.
     * 
     * @return (ServeEvent|WaitEvent|LeaveEvent) Next event to execute after this
     */
    @Override
    public Event execute() {
        // Get next available server, and return a ServeEvent if exists
        final Server nextAvailableServer = ServerList.getNextAvailableServer(this.servers);
        if (nextAvailableServer != null)
            // ServeEvent will make server from available to queueAvailable
            return new ServeEvent(this.customer, this.servers, nextAvailableServer.identifier, this.startTime, false);

        // Get next server with available queue, and return a WaitEvent if exists
        final Server nextAvailableQueueServer = ServerList.getnextAvailableQueueServer(this.servers);
        if (nextAvailableQueueServer != null)
            // WaitEvent will make server from queueAvailable to Full
            return new WaitEvent(this.customer, this.servers, nextAvailableQueueServer.identifier, this.startTime);

        // If no Available server OR Queue, return LeaveEvent
        return new LeaveEvent(this.customer, this.servers, this.startTime);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d arrives", this.customer.arrivalTime, this.customer.customerID);
    }
}
