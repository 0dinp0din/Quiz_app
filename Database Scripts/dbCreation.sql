CREATE DATABASE IF NOT EXISTS quizDb;
CREATE USER 'quizmaster' IDENTIFIED BY 'password';
GRANT ALL on quizDb.* TO 'quizmaster';