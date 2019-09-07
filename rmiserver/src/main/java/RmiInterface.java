import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RmiInterface extends Remote {
    List<User> getUsersFromDatabase() throws RemoteException;

    List<User> getUsersFromTextFile() throws RemoteException, FileNotFoundException;
}
