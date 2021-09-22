CREATE DATABASE IF NOT EXISTS learn_db;
use learn_db;
CREATE TABLE  IF NOT EXISTS t_persons
(
    Id_P int,
    LastName varchar(255),
    FirstName varchar(255),
    Address varchar(255),
    City varchar(255)
);
CREATE TABLE IF NOT EXISTS t_paper
(
    paper_id int NOT NULL AUTO_INCREMENT,
    name varchar(255),
    number varchar(255),
    detail varchar(255),
    PRIMARY KEY  (`paper_id`)
);