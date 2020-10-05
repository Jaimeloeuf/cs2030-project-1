package simulator;

import java.util.List;

/**
 * Utility class with static methods for a more project specific interface to
 * the ServerList for easy reuse.
 * 
 * These methods will directly mutate the original serverList if needed. Since
 * the serverList is a pointer/reference, modifying objects in the list, will be
 * reflected in all code that have a reference to the server list.
 */
class ServerList {

    /**
     * Get a server object from ServerList using serverID
     */
    public static Server getServerByID(List<Server> serverList, int serverID) {
        return serverList.stream().filter(server -> server.identifier == serverID).findAny().orElse(null);
    }

    /**
     * Return a server that is available using a Functional method
     */
    public static Server getNextAvailableServer(List<Server> serverList) {
        return serverList.stream().filter(server -> server.status == Server.Status.available).findAny().orElse(null);
    }

    /**
     * Return server with an open spot in its Queue using a Functional method
     */
    public static Server getnextAvailableQueueServer(List<Server> serverList) {
        return serverList.stream().filter(server -> server.status == Server.Status.queueAvailable).findAny()
                .orElse(null);
    }

    /**
     * Update status of a server in the serverList.
     * 
     * https://stackoverflow.com/questions/49735245/java-8-stream-indexof-method-based-on-predicate
     * NOTE: no predicate based indexOf API available, thus using a for loop.
     */
    public static void updateServer(List<Server> serverList, int serverIdentifier, Server updatedServer) {

        for (int i = 0; i < serverList.size(); i++)
            if (serverList.get(i).identifier == serverIdentifier) {
                serverList.set(i, updatedServer);
                return; // Break out of method as no need to check other servers, assuming ID is unique
            }
    }
}