import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Database {
    Connection dbconnection;

    String DBuser = "quizmaster";
    String DBpassword = "password";


    public static void loadDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Database() {
        Database.loadDriver();
        try {
            dbconnection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/quizDb?allowPublicKeyRetrieval=true&useSSL=false" +
                            "&allowMultiQueries=true", DBuser, DBpassword);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //This method checks if quiz data exists and inserts data if it doesn't.
    public void quizDataExists() throws SQLException {
        String query = "SELECT * FROM multichoiceQuiz";

        PreparedStatement checkData = dbconnection.prepareStatement(query);
        ResultSet rs = checkData.executeQuery();
        if (!rs.next()) {
            PreparedStatement insertMCData = dbconnection.prepareStatement(QuizData.multichoiceData);
            PreparedStatement insertBCData = dbconnection.prepareStatement(QuizData.binaryData);
            insertMCData.executeUpdate();
            insertBCData.executeUpdate();
        }
    }

    ResultSet fetch(HashMap<Integer, String> params, String query) throws SQLException{

        PreparedStatement statement = dbconnection.prepareStatement(query);

        for (Map.Entry<Integer, String> set : params.entrySet()) {
            statement.setString(set.getKey(), set.getValue());
        }

        return statement.executeQuery();
    }

    void update(HashMap<Integer, String> params, String query) throws SQLException{

        PreparedStatement statement = dbconnection.prepareStatement(query);

        for (Map.Entry<Integer, String> set : params.entrySet()) {
            statement.setString(set.getKey(), set.getValue());
        }

        statement.executeUpdate();
    }
}
