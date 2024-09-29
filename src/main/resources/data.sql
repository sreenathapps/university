-- Insert Professor data
INSERT INTO Professor (name, department) VALUES
  ('John Smith', 'Computer Science'),
  ('Mary Johnson', 'Physics'),
  ('David Lee', 'Mathematics');

-- Insert Course data
INSERT INTO Course (name, credits, professorId) VALUES
  ('Introduction to Programming', 3, 1),
  ('Quantum Mechanics', 4, 2),
  ('Calculus', 4, 3);

-- Insert Student data
INSERT INTO Student (name, email) VALUES
  ('Alice Johnson', 'alice@example.com'),
  ('Bob Davis', 'bob@example.com'),
  ('Eva Wilson', 'eva@example.com');

-- Insert Junction table data
INSERT INTO course_student (courseId, studentId) VALUES
  (1, 1),
  (1, 2),
  (2, 2),
  (2, 3),
  (3, 1),
  (3, 3);