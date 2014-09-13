# Magic the Gathering® Database API Project #

### Summary ###

* This project encapsulates shared objects which model and access a database of Magic the Gathering® cards.
* Version: 1.0.0

### Build Instructions ###

* Environment Setup
    * You can run the src/main/scripts/setup.sh BASH script to check for required dependencies, and automatically download and build related projects.
* Database configuration
    * The project depends on the existence of the MagicDB database, but does not automatically create it.
    * The database must be MySQL version 5.5 or above
    * Due to the multilingual nature of Magic the Gathering®, the database must be configured as UTF-8.
    * Database creation script is located in src/main/sql
    * Database properties are located in src/main/resources/magicdb.properties
    * Spring JDBC properties are located in src/main/resources/spring-dbconfig.xml
* Configuration
    * Spring, Logback, and database properties files are located in src/main/resources/
        * Spring configuration (including Spring JDBC configuration) is shared across all MagicDB projects.
    * Maven configuration is split between a parent_pom.xml and child pom.xml at the root of the project.
        * The parent Maven POM is shared across all MagicDB projects.
* Dependencies
    * Dependencies are loaded by Maven, and documented in the parent and child POM files.
    * Current Dependencies:
        * Spring JDBC
        * Spring MVC
        * MySQL Connector
        * Apache Commons
        * JUnit
        * Spring Test
        * SLF4J
        * Logback
* How to run tests
    * Unit tests now include a mock database, so they can all run independently.
    * Unit tests run as part of the Maven build.
* Deployment instructions
    * The Parent POM will need to be installed in the local Maven repository for any of the MagicDB projects to build.
    * Maven build targets:
        * -f parent_pom.xml install   
    * The API JAR should be installed in your local Maven repository to be available for the other MagicDB projects to include.
    * Maven build targets:
        * clean install
