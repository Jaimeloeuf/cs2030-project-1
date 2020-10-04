# CS2030 Java Project
My attempt of this project. [Ques is a html page](./Ques)

## Build and run with
javac *.java -d build;
javac .\Main.java -d .\build\;
java -cp .\build\ Main;

## Notes
- execute updates the state of the servers, and returns a new event to be chained / added to queue to operate
- Problem: For event transitions like from Done to Serve, the server is updated but since Serve is instantiated in Wait event, passing in a currentServer object will just get outdated by the time "execute" method is executed.
    - Solution: Pass in and Store serverID instead for Event instantiations and use it to get server object from ServerList that is garunteed to always be updated.
    - ServerList.getServerByID(this.servers, this.serverID);
- on every execute method, if the current server is needed, use the serverID to get it from the ServerList.getServerByID method, to ensure that the currentServer is always the latest updated version of the server object.

## License
MIT, do whatever you want with this. Just perhaps dont copy for the same mod 😂?