import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RmiImplementation extends UnicastRemoteObject implements RmiInterface {

    public RmiImplementation() throws RemoteException
    {
        super();
    }

    @Override
    public List<User> getUsersFromDatabase() throws RemoteException {
        List<User> users = new ArrayList<>();
        try {
            ResultSet rs = MySQLConnection.createStatement("select * from student");
            while (rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public List<User> getUsersFromTextFile() throws RemoteException, FileNotFoundException {
        Type USER_TYPE = new TypeToken<List<User>>() {
        }.getType();
        Gson gson = new Gson();

        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(Objects.requireNonNull(classLoader.getResource("students.json")).getFile());

        FileReader reader = new FileReader(file);

        JsonReader jr = new JsonReader(reader);

        List<User> users = gson.fromJson(jr, USER_TYPE);
        return users;
    }
}
