
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

    Event(Customer customer, List<Server> servers) {
        this.customer = customer;
        this.servers = servers;
    }

    // Default method that can be overriden to suit the different events
    // If not overriden, provides a default NOOP method in place to prevent calling
    // on unimplemented method
    public Event execute() {
        // Not throwing the "UnimplementedMethodException" as some events can choose to
        // leave this unimplemented but still want to have the method on its class to be
        // called by a generic event caller
        return null;
    }

    // Abstract method to be implemented by the specific concrete Event classes
    public abstract String toString();
}
