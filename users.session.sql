CREATE TABLE users (
	id serial PRIMARY KEY,
	username VARCHAR (64) UNIQUE NOT NULL,
	password VARCHAR (128) NOT NULL,
	first_name VARCHAR (128) NOT NULL,
	last_name VARCHAR (128),
	email VARCHAR (64) UNIQUE NOT NULL,
	manager_id BIGINT NOT NULL,
	status VARCHAR (1) NOT NULL,
	created_by VARCHAR (128) NOT NULL,
	created_at TIMESTAMP NOT NULL,
	modified_by VARCHAR (128) NOT NULL,
	modified_at TIMESTAMP
);