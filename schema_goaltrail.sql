ALTER TABLE IF EXISTS annual DROP CONSTRAINT IF EXISTS FK_users_annual;
DROP TABLE IF EXISTS annual;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE IF NOT EXISTS users (
  user_id SERIAL NOT NULL,
  authority VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS annual
(
  annual_id SERIAL NOT NULL,
  user_id INTEGER NOT NULL,
  year INTEGER NOT NULL,
  annual_goal VARCHAR (255) NOT NULL,
  status BOOLEAN NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
   PRIMARY KEY (annual_id)
);
ALTER TABLE annual ADD CONSTRAINT FK_users_annual FOREIGN KEY (user_id) REFERENCES users(user_id);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO goaltrail;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO goaltrail;