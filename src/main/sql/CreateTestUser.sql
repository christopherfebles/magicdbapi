/* TestUser can be used while debugging to check the contents of the embedded database */

/* CREATE USER 'testuser'@'localhost' IDENTIFIED BY 'testpassword'; */
GRANT ALL PRIVILEGES ON *.* TO 'testuser'@'localhost' IDENTIFIED BY 'testpassword';
FLUSH PRIVILEGES;