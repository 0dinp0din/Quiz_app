import java.sql.*;
import java.util.*;

public class BinaryQuiz implements Quiz{

    int points = 0;

    List<String> allowedOptions = new ArrayList<String>(4);

    public void setAllowedOptions(){
        allowedOptions.add("yes");
        allowedOptions.add("no");
    }

    String topic;
    String user;

    public BinaryQuiz(String topic, String user) {
        this.topic = topic;
        this.user = user;
    };

    @Override
    public void showQuestion() throws SQLException {
        HashMap<Integer, String> cache = new HashMap<>();
        setAllowedOptions();

        String query = "SELECT * FROM binaryQuiz WHERE topic = ? ORDER BY RAND();";
        System.out.printf("Welcome to the %s quiz! ", topic);
        System.out.println("Please reply yes/no to the following questions:\n");

        cache.put(1, topic);

        ResultSet questions = database.fetch(cache, query);
        while (questions.next()) {
            System.out.println(questions.getString("question"));

            String userAnswer = input.nextLine();

            while (!allowedOptions.contains(userAnswer.toLowerCase())) {
                System.out.println("That was not an option.. Try again.");
                userAnswer = input.nextLine();
            }

            if (isCorrectAnswer(userAnswer, questions.getString("correctAnswer"))){
                points++;
            }
        }
        System.out.printf("You finished with %s points!", points);
        addScore(user, points, topic);
    }

    @Override
    public boolean isCorrectAnswer(String userInput, String answer) {
        if (Objects.equals(userInput.toLowerCase(), answer)) {
            System.out.println("Correct!\n");
            return true;
        }
        System.out.println("Incorrect!");
        return false;
    }

    @Override
    public void addScore(String user, int points, String topic) throws SQLException {
        HashMap<Integer, String> pointCache = new HashMap<>();
        String saveScore = "INSERT INTO scoreHistory(username, score, topic) VALUES (?, ?, ?);";

        pointCache.put(1, user);
        pointCache.put(2, String.valueOf(points));
        pointCache.put(3, topic);

        database.update(pointCache, saveScore);

    }
}
