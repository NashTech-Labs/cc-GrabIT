CREATE TABLE IF NOT EXISTS user (
  id VARCHAR(20) PRIMARY KEY,
  access_token TEXT,
  employee_id VARCHAR(20),
  name TEXT,
  email TEXT,
  password TEXT,
  role VARCHAR(20),
  created_at timestamp,
  last_modified_at timestamp
);
