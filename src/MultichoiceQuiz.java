import java.sql.*;
import java.util.*;

public class MultichoiceQuiz implements Quiz{

    int points = 0;

    List<String> allowedOptions = new ArrayList<>(4);

    public void setAllowedOptions(){
        allowedOptions.add("A");
        allowedOptions.add("B");
        allowedOptions.add("C");
        allowedOptions.add("D");
    }

    String topic;
    String user;

    public MultichoiceQuiz(String topic, String user) {
    this.topic = topic;
    this.user = user;
    }

    @Override
    public void showQuestion() throws SQLException {
        HashMap<Integer, String> cache = new HashMap<>();
        setAllowedOptions();

        String query = "SELECT * FROM multichoiceQuiz WHERE topic = ? ORDER BY RAND();";
        System.out.printf("Welcome to the %s quiz! ", topic);

        cache.put(1, topic);

        ResultSet questions = database.fetch(cache, query);
        while (questions.next()) {
            System.out.println(questions.getString("question") + "\n" +
                    "A." + questions.getString("answerA") + "\n" +
                    "B." + questions.getString("answerB") + "\n" +
                    "C." + questions.getString("answerC") + "\n" +
                    "D." + questions.getString("answerD"));

            String userAnswer = input.nextLine();

            while (!allowedOptions.contains(userAnswer.toUpperCase())) {
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
        if (Objects.equals(userInput.toUpperCase(), answer)) {
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
