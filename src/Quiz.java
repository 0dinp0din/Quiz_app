import java.sql.SQLException;
import java.util.Scanner;

public interface Quiz {

    Database database = new Database();
    Scanner input = new Scanner(System.in);

    public void showQuestion() throws SQLException;

    public boolean isCorrectAnswer(String userInput, String answer);

    public void addScore(String user, int points, String topic) throws SQLException;

}
