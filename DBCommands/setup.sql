# This is used to initialize the pastor user for the Datase
# login as root and source setupUser.sql;

CREATE DATABASE IF NOT EXISTS CLogger;

GRANT USAGE ON *.* TO 'pastor'@127.0.0.1;

DROP USER 'pastor'@127.0.0.1;

CREATE USER 'pastor'@127.0.0.1 identified by 'loggingrox';

GRANT ALL privileges on *.* to 'pastor'@127.0.0.1 IDENTIFIED BY 'loggingrox' WITH grant OPTION;
