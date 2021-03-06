CREATE TABLE IF NOT EXISTS vhs (
	id SERIAL PRIMARY KEY,
	title VARCHAR(500) NOT NULL,
	plot VARCHAR(2000),
	year INTEGER NOT NULL,
	UNIQUE(title, plot, year)
);

CREATE TABLE IF NOT EXISTS vhs_user (
	id SERIAL PRIMARY KEY,
	username VARCHAR(50) UNIQUE NOT NULL,
	password VARCHAR(500) NOT NULL,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	role VARCHAR(5) NOT NULL
);

CREATE TABLE IF NOT EXISTS rental (
	id SERIAL PRIMARY KEY,
	vhs_id INTEGER REFERENCES vhs(id),
	user_id INTEGER REFERENCES vhs_user(id),
	date_rented TIMESTAMP NOT NULL,
	due_date TIMESTAMP NOT NULL,
	date_returned TIMESTAMP,
	late_fee NUMERIC(19, 2)
);