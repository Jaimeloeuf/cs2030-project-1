//package cs2030.simulator;

// Immutable Server class
class Server {
    // Blank final variables to be assigned in the constructor
    // Made immutable using the "final" modifier
    public final int identifier;
    public final boolean isAvailable;
    public final boolean hasWaitingCustomer;
    public final double nextAvailableTime;

    public static enum Status {
        available, queueAvailable, full
    }

    /**
     * @param identifier         the identifier of the server
     * @param isAvailable        whether the server is currently available to serve
     * @param hasWaitingCustomer is there a waiting customer while server is serving
     * @param nextAvailableTime  while server is serving, time to finish the current
     *                           service (the time when server is able to serve the
     *                           next customer)
     */
    Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime) {
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
    }

    public Status status() {
        if (this.isAvailable)
            return Status.available;
        // Serving someone but the queue is empty
        else if (!this.isAvailable && !this.hasWaitingCustomer)
            return Status.queueAvailable;
        // Serving someone and someone else is in the queue
        else if (!this.isAvailable && this.hasWaitingCustomer)
            return Status.full;
        else
            throw new Error("INVALID SERVER STATUS: " + this.isAvailable + " " + this.hasWaitingCustomer);
    }

    public String toString() {
        switch (this.status()) {
            case available:
                return String.format("%d is available", this.identifier);
            case queueAvailable:
                return String.format("%d is busy; available at %.3f", this.identifier, this.nextAvailableTime);
            case full:
                return String.format("%d is busy; waiting customer to be served at %.3f", this.identifier,
                        this.nextAvailableTime);

            // Technically this is not be needed since this.status() is GARUNTEED to return
            // a value from the Status enum, but adding this to prevent Java from throwing
            // an error because toString expects a string to be returned or an error thrown.
            default:
                throw new Error("INVALID SERVER STATUS, prevents toString");
        }
    }
}
