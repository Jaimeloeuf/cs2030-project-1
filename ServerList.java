import java.util.List;

class ServerList {

    public static Server getServerByID(List<Server> serverList, int serverID) {
        return serverList.stream().filter(server -> server.identifier == serverID).findAny().orElse(null);
    }

    // Return a server that is available using a Functional method
    public static Server getNextAvailableServer(List<Server> serverList) {
        return serverList.stream().filter(server -> server.status() == Server.Status.available).findAny().orElse(null);
    }

    // Return server with an open spot in its Queue using a Functional method
    public static Server getnextAvailableQueueServer(List<Server> serverList) {
        return serverList.stream().filter(server -> server.status() == Server.Status.queueAvailable).findAny()
                .orElse(null);
    }

    /**
     * Update status of a server in the serverList.
     * 
     * Might modify the objects on the serverList.
     * 
     * @param server
     */
    public static List<Server> updateServer(List<Server> serverList, int serverIdentifier, Server updatedServer) {
        // Seems like no indexOf API available
        // https://stackoverflow.com/questions/49735245/java-8-stream-indexof-method-based-on-predicate

        for (int i = 0; i < serverList.size(); i++)
            if (serverList.get(i).identifier == serverIdentifier) {
                serverList.set(i, updatedServer);
                break; // Break out of list as no need to check other servers, assuming ID is unique
            }

        // Return the serverList reference to do chaining method calls
        return serverList;
    }
}