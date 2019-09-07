import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

public class RmiClient {
    public static void getServiceAndData(Types type) throws Exception {
        // name =  rmi:// + ServerIP +  /EngineName;
        String remoteEngine = "rmi://localhost:1099/ServerEngine";
        // Create local stub, lookup in the registry searching for the remote engine - the interface with the methods we want to use remotely
        RmiInterface obj = (RmiInterface) Naming.lookup(remoteEngine);

        if (type == Types.DATABASE_STUDENTS) {
            obj.getUsersFromDatabase();
            List<User> result = obj.getUsersFromDatabase();
            System.out.println("Print students from database:");
            result.forEach(ele -> System.out.println("ID: " + ele.getId() + " firstName: " + ele.getFirstName() + " lastName: " + ele.getLastName() + " email " + ele.getEmail()));
        }

        if (type == Types.JSON_STUDENTS) {
            List<User> result = obj.getUsersFromTextFile();
            System.out.println("Print students from json:");
            result.forEach(ele -> System.out.println("ID: " + ele.getId() + " firstName: " + ele.getFirstName() + " lastName: " + ele.getLastName() + " email " + ele.getEmail()));
        }

    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        label:
        while (true) {
            System.out.println("Enter choice to get from RMI server:");
            System.out.println("1. Load students from database");
            System.out.println("2. Load students from text file");
            System.out.println("3. Exit");
            String line = scan.nextLine();
            switch (line) {
                case "1":
                    getServiceAndData(Types.DATABASE_STUDENTS);
                    break;
                case "2":
                    getServiceAndData(Types.DATABASE_STUDENTS);
                    break;
                case "3":
                    break label;
                default:
                    System.out.println("I didnt get your latest input, please try again");
                    break;
            }
        }
    }
}
