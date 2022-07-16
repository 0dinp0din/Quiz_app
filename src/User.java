import java.sql.*;
import java.util.*;

public class User {
    Database database = new Database();
    Scanner input = new Scanner(System.in);

    Quiz quiz;
    int id;

    String username;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    };


    public void selectQuiz() throws SQLException {
        String query;
        int index = 0;
        HashMap<Integer, String> dboutput = new HashMap<>();

        System.out.println("""
                Select a quiz type:
                1. Multiple choice quiz
                2. Single choice quiz""");

        String typeOption = input.nextLine();

        if (Objects.equals(typeOption, "1")) {
            query = "SELECT DISTINCT topic FROM multichoiceQuiz;";
            } else if (Objects.equals(typeOption, "2")) {
            query = "SELECT DISTINCT topic FROM binaryQuiz;";
        } else {
            System.out.println("That was not one of the options..");
            return;
        };

        PreparedStatement statement = database.dbconnection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            index++;
            System.out.println("Select one of the following topics:");
            System.out.println(index + ". " +rs.getString("topic"));
            dboutput.put(index, rs.getString("topic"));
        }

        String topicOption = input.nextLine();
        while (!dboutput.containsKey(Integer.parseInt(topicOption))){
            System.out.println("That was not an option.. Try again");
            topicOption = input.nextLine();
        };

        if (Objects.equals(typeOption, "1")) {
            quiz = new MultichoiceQuiz(dboutput.get(Integer.parseInt(topicOption)), this.username);
        } else if (Objects.equals(typeOption, "2")) {
            quiz = new BinaryQuiz(dboutput.get(Integer.parseInt(topicOption)), this.username);
        }
    };

    public void showScoreboard() throws SQLException {
        int index = 0;
        String fetchScore = "SELECT username, score, topic FROM scoreHistory ORDER BY score DESC LIMIT 10;";

        ResultSet scoreDB = database.dbconnection.createStatement().executeQuery(fetchScore);
        System.out.println("Top 10 scores:");
        while (scoreDB.next()) {
            index++;
            System.out.println(index + ". User: "+scoreDB.getString("username")
                    +" Score: "+ scoreDB.getString("score")
                    +" Topic: "+ scoreDB.getString("topic")
            );
        }
        System.out.println("--------------------------");
    }

    public void showOwnScores() throws SQLException {
        int index = 0;
        String fetchSelfScore = "SELECT username, score, topic FROM scoreHistory  WHERE username = ? DESC ORDER BY score";
        HashMap<Integer, String> fetchScoreCache = new HashMap<>();

        fetchScoreCache.put(1, username);

        ResultSet selfScoreDB = database.fetch(fetchScoreCache, fetchSelfScore);

        System.out.println("Your scores:");
        while (selfScoreDB.next()) {
            index++;
            System.out.println(index + ". User: "+selfScoreDB.getString("username")
                    +" Score: "+ selfScoreDB.getString("score")
                    +" Topic: "+ selfScoreDB.getString("topic")
            );
        }
        System.out.println("--------------------------");
    }

    public void option() throws SQLException {
        boolean exit = false;

        while (!exit){
            System.out.println("""
                        Please select an action below:
                        1. Play a quiz!
                        2. List your scores
                        3. See top 10 scores for all users
                        4. Exit application""");

            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();

            switch (option) {
                case "1" -> {
                    selectQuiz();
                    quiz.showQuestion();
                    showScoreboard();
                }case "2" -> {
                    showOwnScores();
                }case "3" -> {
                    showScoreboard();
                }case "4" -> {
                    System.out.println("Goodbye! Please come back for more quizzing!");
                    exit = true;
                }
            }
        }
    }
}
