
//package cs2030.simulator;
import java.util.List;

// Abstract class to be extended to create different Event classes
abstract class Event {
    // Blank final variables to be assigned in the constructor
    // Made immutable using the "final" modifier
    public final Customer customer;
    public final List<Server> servers;

    // This value will be set in execute method, using the servers list, next
    // available time value
    // @todo How to make this immutable?
    //
    public double startTime;

    // Field to hold a reference to the current server object.
    // Set --> this.currentServer = ServerList.getServerByID(servers, serverID);
    public Server currentServer;

    Event(Customer customer, List<Server> servers) {
        this.customer = customer;
        this.servers = servers;
    }

    /* Abstract methods to be implemented by the specific concrete Event classes */
    public abstract Event execute();

    public abstract String toString();
}
