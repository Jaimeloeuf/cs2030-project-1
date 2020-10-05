package simulator;

import java.util.List;

// Abstract class to be extended to create different Event classes
public abstract class Event {
    // Blank final variables to be assigned in the constructor
    // Made immutable using the "final" modifier
    public final Customer customer;
    public final List<Server> servers;

    /*
     * Always set in constructors of Event subclasses.
     * 
     * Make this immutable by setting this as "final" and moving it into every
     * single event subclass to set it during their instantiation.
     * 
     * BREAKING projects requirement for immutatbility here as it is cleaner to
     * define here and inherit it in every Event Subclass instead of defining it in
     * every Event subclass. This cannot be set in Event constructor as the Event
     * constructor has a fixed interface defined in the project spec. And as long we
     * make sure that no one else sets this value except for the subclass
     * constructors, it keeps the overall code simpler and more unified.
     * 
     * Another solution involves making this a private variable and using Setter
     * traps to prevent write of more then once. But would require all accessors to
     * use getter methods, which is uglier with all the wasted function call stacks.
     */
    public double startTime;

    // Refer to comment block above on immutatbility.
    // currentServer serverID of that Event subclass to get object from ServerList
    public int serverID;

    Event(Customer customer, List<Server> servers) {
        this.customer = customer;
        this.servers = servers;
    }

    /* Abstract methods to be implemented by the specific concrete Event classes */
    public abstract Event execute();

    public abstract String toString();
}
