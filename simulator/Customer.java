package simulator;

// Immutable Customer class to represent each arriving customer tagged with a customer ID (int) and an arrival time (double).
public class Customer {
    // Blank final variables to be assigned in the constructor
    // Made immutable using the "final" modifier
    public final int customerID;
    public final double arrivalTime;

    public Customer(int customerID, double arrivalTime) {
        this.arrivalTime = arrivalTime;
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return String.format("%d arrives at %.3f", this.customerID, this.arrivalTime);
    }
}
