-- Drop tables if they exist, ignoring any errors that occur
DROP TABLE IF EXISTS course_student;
DROP TABLE IF EXISTS Course;
DROP TABLE IF EXISTS Student;
DROP TABLE IF EXISTS Professor;

-- Create tables
CREATE TABLE Professor (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  name TEXT,
  department TEXT
);

CREATE TABLE Course (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  name TEXT,
  credits INTEGER,
  professorId INTEGER,
  FOREIGN KEY (professorId) REFERENCES Professor(id)
);

CREATE TABLE Student (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  name TEXT,
  email TEXT
);

CREATE TABLE course_student (
  studentId INTEGER,
  courseId INTEGER,
  PRIMARY KEY (studentId, courseId),
  FOREIGN KEY (studentId) REFERENCES Student(id),
  FOREIGN KEY (courseId) REFERENCES Course(id)
);