
//package cs2030.simulator;
import java.util.List;
import java.util.Arrays;

class ArriveEvent extends Event {
    // @todo Missing arrived time

    /**
     * private field to ensure no one else but the internal methods can modify this
     * public getter available for this to give READ only access to external classes
     * static variable, as this is shared across all instances of this class
     */
    private static int numberOfCustomersLeftWithoutService = 0;

    ArriveEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
    }

    /**
     * @return New event to be executed after this arrive event
     */
    @Override
    public Event execute() {
        Event event = null;

        // Loop through all servers
        for (int i = 0; i < this.servers.size(); i++) {
            switch (this.servers.get(i).status()) {
                case available:
                    // If it any of the servers are available, use it immediately and break out of
                    // the loop
                    return new ServeEvent(this.customer, Arrays.asList(this.servers.get(i)), true);
                case queueAvailable:
                    // If there is a server with a available queue spot, store this event first, but
                    // dont use it yet, to see if the next server have a better option of a
                    // available status
                    event = new WaitEvent(this.customer, Arrays.asList(this.servers.get(i)));
                    break;
                case full:
                    // Store this event first, and overwrite it, if there is a available or
                    // queueAvailable server next.
                    // But if there is a WaitEvent currenty stored, DO NOT overwrite it. Only
                    // overwrite if there is no other events stored
                    if (!(event instanceof WaitEvent))
                        event = new LeaveEvent(this.customer, Arrays.asList(this.servers.get(i)));
                    break;

                // Technically this is not be needed since this.status() is GARUNTEED to return
                // a value from the Status enum, but adding this to prevent Java from throwing
                // an error because toString expects a string to be returned or an error thrown.
                default:
                    throw new Error("INVALID SERVER STATUS, prevents toString");
            }

        }

        // Increment "numberOfCustomersLeftWithoutService" when the leave event is to be
        // returned after executing this current ArriveEvent.
        if (event instanceof LeaveEvent)
            ++numberOfCustomersLeftWithoutService;

        return event;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d arrives", this.customer.arrivalTime, this.customer.customerID);
    }

    // "numberOfCustomersLeftWithoutService" static variable Getter for statistics
    public static int getNumberOfCustomersLeftWithoutService() {
        return numberOfCustomersLeftWithoutService;
    }
}
