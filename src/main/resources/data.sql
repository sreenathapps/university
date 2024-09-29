-- Insert Professor data
INSERT INTO Professor (id, name, department) VALUES
  (1, 'John Smith', 'Computer Science'),
  (2, 'Mary Johnson', 'Physics'),
  (3, 'David Lee', 'Mathematics');

-- Insert Course data
INSERT INTO Course (id, name, credits, professorId) VALUES
  (1, 'Introduction to Programming', 3, 1),
  (2, 'Quantum Mechanics', 4, 2),
  (3, 'Calculus', 4, 3);

-- Insert Student data
INSERT INTO Student (id, name, email) VALUES
  (1, 'Alice Johnson', 'alice@example.com'),
  (2, 'Bob Davis', 'bob@example.com'),
  (3, 'Eva Wilson', 'eva@example.com');

-- Insert Junction table data
INSERT INTO course_student (courseId, studentId) VALUES
  (1, 1),
  (1, 2),
  (2, 2),
  (2, 3),
  (3, 1),
  (3, 3);