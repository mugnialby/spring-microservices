CREATE TABLE roles (
	id serial PRIMARY KEY,
	role_name VARCHAR (64) NOT NULL,
	status VARCHAR (1) NOT NULL,
	created_by VARCHAR (128) NOT NULL,
	created_at TIMESTAMP NOT NULL,
	modified_by VARCHAR (128),
	modified_at TIMESTAMP
);

DROP TABLE roles;

SELECT * FROM ROLES;