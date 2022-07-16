import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    Scanner input = new Scanner(System.in);
    UserManagement usermgmt = new UserManagement();


    void welcome() throws SQLException {
        System.out.println("Welcome to the quiz game! Please select an option:\n1. Create account\n2. Login");
        String option = input.nextLine();

        while (!Objects.equals(option, "1") && !Objects.equals(option, "2")) {
            System.out.println("That was not one of the options.. Try again");
            option = input.nextLine();
        }

        if (Objects.equals(option, "1")){
            registration();
            login();
        } else if (Objects.equals(option, "2")){
            login();
        }
    }

    void registration() throws SQLException {
        System.out.println("To create an account, type your details below:");
        System.out.print("Username: ");
        String username = input.next();

        System.out.print("Password: ");
        String password = input.next();

        usermgmt.registerUser(username, password);
    }

    void login() throws SQLException {
        boolean authenticated = false;
        User user = null;
        System.out.println("Type your details below to log in:");

        while (user == null) {
            System.out.print("Username: ");
            String username = input.next();

            System.out.print("Password: ");
            String password = input.next();

            user = usermgmt.login(username, password);
            if (user != null) {
                authenticated = true;
            }
        }
        user.option();
    }
}