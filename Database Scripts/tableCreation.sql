USE quizDb;

CREATE TABLE IF NOT EXISTS users(
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
username VARCHAR(50),
pass VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS multichoiceQuiz(
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
topic VARCHAR (200),
question VARCHAR(200),
answerA VARCHAR(200),
answerB VARCHAR(200),
answerC VARCHAR(200),
answerD VARCHAR(200),
correctAnswer VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS binaryQuiz(
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
topic VARCHAR(200),
question VARCHAR(200),
correctAnswer VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS scoreHistory(
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
username VARCHAR(200),
score VARCHAR(200),
topic VARCHAR(200)
);