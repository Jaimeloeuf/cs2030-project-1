import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;

class ServerList {
    public final List<Server> serverList;

    ServerList(List<Server> servers) {
        this.serverList = servers;
    }

    public Server getNextAvailableServer() {
        // Procedural way vs functional way
        // for (Server server : this.serverList)
        // if (server.isAvailable)
        // return server;
        // return null;

        return serverList.stream().filter(server -> server.isAvailable).findAny().orElse(null);
    }

    /**
     * Update status of a server in the serverList
     * 
     * @param server
     */
    public void updateServer(int serverIdentifier, Server updatedServer) {
        // Seems like no indexOf API available
        // https://stackoverflow.com/questions/49735245/java-8-stream-indexof-method-based-on-predicate

        for (int i = 0; i < this.serverList.size(); i++)
            if (this.serverList.get(i).identifier == serverIdentifier) {
                this.serverList.set(i, updatedServer);
                // Return here instead of chaining in front of the set method call because the
                // set method returns the previous Server object at the location, which will
                // violate this method's interface of a void return type.
                return;
            }
    }
}

class Main {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);

        int numOfServers = sc.nextInt();

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
        }
    }

    public static void getStatistics() {
        // Statistics 1, "the average waiting time for customers who have been served"
        final double stat1 = ServeEvent.getNumberOfCustomersServed();

        // Statistics 2, "the number of customers served"
        final int stat2 = ServeEvent.getNumberOfCustomersServed();

        // Statistics 3, "the number of customers who left without being served"
        final int stat3 = ArriveEvent.getNumberOfCustomersLeftWithoutService();

        System.out.printf("[%.3f %d %d]\n", stat1, stat2, stat3);
    }
}
