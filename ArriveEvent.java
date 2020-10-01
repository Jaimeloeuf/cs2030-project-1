
//package cs2030.simulator;
import java.util.List;
import java.util.Arrays;

class ArriveEvent extends Event {
    // @todo Missing arrived time

    ArriveEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
    }

    @Override
    public Event execute() {
        Event event = null;

        // Loop through all servers
        for (int i = 0; i < super.servers.size(); i++) {
            switch (super.servers.get(i).status()) {
                case available:
                    // If it any of the servers are available, use it immediately and break out of
                    // the loop
                    return new ServeEvent(super.customer, Arrays.asList(super.servers.get(i)), true);
                case queueAvailable:
                    // If there is a server with a available queue spot, store this event first, but
                    // dont use it yet, to see if the next server have a better option of a
                    // available status
                    event = new WaitEvent(super.customer, Arrays.asList(super.servers.get(i)));
                    break;
                case full:
                    // Store this event first, and overwrite it, if there is a available or
                    // queueAvailable server next.
                    // But if there is a WaitEvent currenty stored, DO NOT overwrite it. Only
                    // overwrite if there is no other events stored
                    if (!(event instanceof WaitEvent))
                        event = new LeaveEvent(super.customer, Arrays.asList(super.servers.get(i)));
                    break;

                // Technically this is not be needed since this.status() is GARUNTEED to return
                // a value from the Status enum, but adding this to prevent Java from throwing
                // an error because toString expects a string to be returned or an error thrown.
                default:
                    throw new Error("INVALID SERVER STATUS, prevents toString");
            }

        }

        return event;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d arrives", super.customer.arrivalTime, super.customer.customerID);
    }
}
