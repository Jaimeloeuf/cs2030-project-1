//package cs2030.simulator;

// Immutable Server class
class Server {
    // All the possible states of the server
    public static enum Status {
        available, queueAvailable, full
    }

    // Blank final variables to be assigned in the constructor
    // Made immutable using the "final" modifier
    public final int identifier;
    public final boolean isAvailable;
    public final boolean hasWaitingCustomer;
    public final double nextAvailableTime;

    // Variable keeping track of the server's status
    // Computed in constructor using constructor arguements.
    public final Status status;

    // Private field to store the String representation of the server.
    // Computed in constructor as this object is immutable, and this field is based
    // on the immutable status value.
    // Private to ensure this can only be accessed via the toString method.
    private final String stringRepresentation;

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

        /*
         * Instead of using a method to compute and return server's status when called.
         * Compute in the constructor and save it as a variable on the object instead.
         * 
         * This is possible BECAUSE this class produces immutable objects, with all of
         * its fields marked as "final" thus there is no possibliity of these changing
         * which means that the moment the server is instantiated, the status of the
         * server is already fixed too and wont change. Thus there is no need to rely on
         * a method to compute it on every read. We can just compute it during
         * instantiation.
         * 
         * Along with setting status, since "toString" method relies on status to return
         * a string, it can be done here to instead of waiting till toString is called.
         * Thus we generate the string representation of each server here and save it on
         * a private field, and allow access via the "toString" "getter"
         */
        if (isAvailable) {
            this.status = Status.available;
            this.stringRepresentation = String.format("%d is available", this.identifier);
        }

        // Serving someone but the queue is empty
        else if (!isAvailable && !hasWaitingCustomer) {
            this.status = Status.queueAvailable;
            this.stringRepresentation = String.format("%d is busy; available at %.3f", this.identifier,
                    this.nextAvailableTime);
        }

        // Serving someone and someone else is in the queue
        else if (!isAvailable && hasWaitingCustomer) {
            this.status = Status.full;
            this.stringRepresentation = String.format("%d is busy; waiting customer to be served at %.3f",
                    this.identifier, this.nextAvailableTime);
        }

        // Dont need, but for extra safety to prevent invalid constructor
        // arguement(s) from creating a invalid status.
        else
            throw new Error("INVALID SERVER STATUS: " + this.isAvailable + " " + this.hasWaitingCustomer);
    }

    public String toString() {
        return this.stringRepresentation;
    }
}
