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

CREATE TABLE IF NOT EXISTS asset (
  id VARCHAR(20) PRIMARY KEY,
  name TEXT,
  unique_name VARCHAR(20),
  type TEXT,
  is_available Boolean,
  created_at timestamp,
  last_modified_at timestamp,
  UNIQUE KEY unique_name_key (unique_name)
);

CREATE TABLE IF NOT EXISTS booking (
  id VARCHAR(20) PRIMARY KEY,
  user_id TEXT,
  asset_id TEXT,
  user_rating int,
  user_feedback TEXT,
  asset_rating int,
  asset_feedback TEXT,
  status TEXT,
  action_performed_by TEXT,
  booking_date timestamp,
  start_time timestamp,
  end_time timestamp,
  finish_time timestamp
);