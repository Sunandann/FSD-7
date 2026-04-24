-- Demo users are inserted through DataInitializer with BCrypt encoded passwords.
-- This no-op table ensures SQL initialization does not fail on an empty script.
CREATE TABLE IF NOT EXISTS bootstrap_marker (
	id INT PRIMARY KEY
);
