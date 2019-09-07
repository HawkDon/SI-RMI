import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.mysql.cj.MysqlConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.stream.Collectors;

public class RmiServer {

    public static Registry registry;

    public RmiServer() throws RemoteException {
    }

    public static void main(String[] args) {
        try {
            System.out.println("RMI server localhost starts");

            // Create a server registry at default port 1099
            registry = LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry created ");

            // Create engine of remote services, running on the server
            RmiImplementation remoteEngine = new RmiImplementation();

            // Give a name to this engine
            String engineName = "ServerEngine";

            // Register the engine by the name, which later will be given to the clients
            Naming.rebind("//localhost/" + engineName, remoteEngine);
            System.out.println("Engine " + engineName + " bound in registry");
        } catch (Exception e) {
            System.err.println("Exception:" + e);
        }
    }

}
