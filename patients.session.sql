CREATE TABLE patients (
	id serial PRIMARY KEY,
	name VARCHAR (128) NOT NULL,
	status VARCHAR (1) NOT NULL,
	created_by VARCHAR (128) NOT NULL,
	created_at TIMESTAMP NOT NULL,
	modified_by VARCHAR (128),
	modified_at TIMESTAMP
);

DROP TABLE patients;

SELECT * FROM patients;