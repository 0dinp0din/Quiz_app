public class QuizData {
    static String multichoiceData = "INSERT INTO multichoiceQuiz (topic, question, answerA, answerB, answerC, answerD, correctAnswer)" +
            "VALUES" +
            "('Technology', 'Which of these is a search engine?', 'FTP', 'Bing', 'Archie', 'ARPANET', 'B')," +
            "('Technology', 'Which of these are not a computer component?', 'RAM', 'GPU', 'CPU', 'Joystick', 'D')," +
            "('Technology', 'What is a JPG?', 'Image file', 'Text file', 'Video file', 'Executable file', 'A')," +
            "('Technology', 'What is the technical term of WWW?', 'Subdomain', 'Path', 'Extention', 'Query', 'A');";

    //fun fact questions source: https://www.welovequizzes.com/yes-or-no-quiz-questions-and-answers/
    static String binaryData = "INSERT INTO binaryQuiz (topic, question, correctAnswer)" +
            "VALUES" +
            "('Fun facts', 'Albert Einstein failed most of the subjects, except for physics and math.', 'yes')," +
            "('Fun facts', 'The first song ever sung in the space was Happy Birthday', 'yes')," +
            "('Fun facts', 'The first country in the world to use postcards was the United States of America.', 'no')," +
            "('Fun facts', 'Tea contains more caffeine than coffee and soda.', 'no');";
}
