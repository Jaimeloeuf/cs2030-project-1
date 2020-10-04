
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
    // @todo How to make this immutable? --> Use a Setter trap, once value set and
    // not null, not allowed to change it
    public double startTime;

    // Field to hold the serverID of the server serving that Event subclass to get
    // the currentServer object back from ServerList
    public int serverID;

    Event(Customer customer, List<Server> servers) {
        this.customer = customer;
        this.servers = servers;
    }

    /* Abstract methods to be implemented by the specific concrete Event classes */
    public abstract Event execute();

    public abstract String toString();
}
