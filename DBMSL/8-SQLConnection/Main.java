import java.util.*;
import java.sql.*;

public class Main {
    public static void main(String args[]) {
        Connection connection = null;
        try {
            Class.forName("com.sql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:sql://10.10.15.96/sql33db", "sql33", "sql33");
            Statement statement = connection.createStatement();
            ResultSet result;
            Scanner sc = new Scanner(System.in);
            String ch = "y";
            while (ch != "n") {
                System.out.println("----------------------------------------------------------");
                System.out.println("1. Print Records");
                System.out.println("2. Add Records");
                System.out.println("3. Update Record");
                System.out.println("4. Delete Record");
                System.out.println("Enter your choice");
                int choice = sc.nextInt();
                if (choice == 1) {
                    String query = "select * from Employee";
                    result = statement.executeQuery(query);
                    while (result.next()) {
                        System.out.println(result.getString("id"));
                        System.out.print(" ");
                        System.out.print(result.getString("name"));
                        System.out.println();
                    }
                } else if (choice == 2) {
                    String name, id;
                    System.out.println("Enter id and name of the employee - ");
                    id = sc.next();
                    name = sc.next();
                    String query = "Insert into Employee values(" + id + "," + " ' " + name + " ');";
                    statement.executeUpdate(query);
                } else if (choice == 3) {
                    String id, name;
                    System.out.println("Enter id of the Employee whose name is to be updated");
                    id = sc.next();
                    System.out.println("Enter new name");
                    name = sc.next();
                    String update = "update Employee set name=" + " ' " + name + " ' " + "where id=" + id + ";";
                    statement.executeUpdate(update);
                } else if (choice == 4) {
                    String id;
                    System.out.println("Enter id of the Employee to be deleted");
                    id = sc.next();
                    String del = "delete from Employee where id=" + id + ";";
                    statement.executeUpdate(del);
                } else {
                    System.out.println("You have entered a wrong choice");
                }
                System.out.println("Do you want to continue (y/n) - ");
                ch = sc.next();
                if (ch == "n") {
                    ch = "n";
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
