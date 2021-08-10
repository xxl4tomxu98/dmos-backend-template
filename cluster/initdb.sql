create database dmos_dev;
create database dmos_test;
create database dmos_prod;

--- Create schemas (First connect to correct database)
create schema dmos_main authorization dmos_backend;