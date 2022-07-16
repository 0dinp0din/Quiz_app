import java.sql.SQLException;

public class ProgramController {
    public static void main(String[] args) throws SQLException {
        Database database = new Database();
        Menu menu = new Menu();

        database.quizDataExists();
        menu.welcome();
    }
}
