import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfServers = sc.nextInt();

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
        }
    }

    public static void getStatistics() {
        // Statistics 1, "the average waiting time for customers who have been served"

        // Statistics 2, "the number of customers served"
        ServeEvent.getNumberOfCustomersServed();

        // Statistics 3, "the number of customers who left without being served"
        ArriveEvent.getNumberOfCustomersLeftWithoutService();
    }
}