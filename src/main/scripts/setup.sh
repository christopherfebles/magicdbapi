#!/bin/bash

echo "Developing on the MagicDB project requires:"
echo "    Java 1.7+"
echo "    Maven"
echo "    Git"
echo "    MySQL 5.5+"
echo ""
echo "To dynamically create the MagicDB database, a localhost MySQL should be running with a magicprogram user created with the default password with full create access."
echo ""
echo "To automatically populate the MagicDB database, an Internet connection will be required."
echo ""
# echo "To automatically deploy the MagicDB web app, a localhost Tomcat will need to be running, with a default 'admin' user created with a blank password and the manager-script role."
# echo ""
echo "This script assumes it is being run inside <Magic project folder>/magicdbapi/src/main/scripts"
echo ""
echo -n "Do you wish to continue? [Y]/n:"
read startScript

if [[ ${startScript} != y* ]] && [[ ${startScript} != Y* ]] && [[ ${startScript} != "" ]]; then
    exit 0
fi
echo ""
echo ""

# Check for Git, Maven, Java, MySQL

# Look for Java
# See: http://stackoverflow.com/questions/7334754/correct-way-to-check-java-version-from-bash-script
if type java >> /dev/null; then
    echo "Success: Found Java executable in PATH"
    _java=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]]; then
    echo "Success: Found Java executable in JAVA_HOME"
    _java="$JAVA_HOME/bin/java"
else
    echo "Fail: Java needs to be installed for the MagicDB app to operate."
    exit 1
fi

# Check that JAVA_HOME is set for proper Maven behavior from the command line
if [[ -z "$JAVA_HOME" ]]; then
    # JAVA_HOME is null
    echo "Fail: JAVA_HOME needs to be set for Maven builds to work properly from the command line."
    exit 1
fi

#Checking for Java 7 or greater
JAVA_VER=$("$_java" -version 2>&1 | sed 's/.*version "\(.*\)\.\(.*\)\..*"/\1\2/; 1q')
if [[ "$JAVA_VER" -ge 17 ]]; then 
    echo "Success: Java is installed and at least version 1.7"
else
    echo "Fail: Java needs to be at least 1.7 for the MagicDB app to operate."
    exit 1
fi

# Check for Maven
if type mvn >> /dev/null; then
    echo "Success: Found Maven executable in PATH"
    _mvn=mvn
elif [[ -n "$M2_HOME" ]] && [[ -x "$M2_HOME/bin/mvn" ]]; then
    echo "Success: Found Maven executable in M2_HOME"
    _mvn="$M2_HOME/bin/mvn"
else
    echo "Fail: Maven needs to be installed for the MagicDB app to build."
    exit 1
fi

# Check for Git
if type git >> /dev/null; then
    echo "Success: Found Git executable in PATH"
    _git=git
elif [[ -n "$GIT_HOME" ]] && [[ -x "$GIT_HOME/bin/git" ]]; then
    echo "Success: Found Git executable in GIT_HOME"
    _git="$GIT_HOME/bin/git"
else
    echo "Fail: Git needs to be installed for the MagicDB app to download related projects."
    exit 1
fi

# Check for MySQL
if type mysql >> /dev/null; then
    echo "Success: Found MySQL executable in PATH"
    _mysql=mysql
    _mysqlConfig=mysql_config_editor
else
    echo "Fail: MySQL needs to be installed for the MagicDB app to operate."
    exit 1
fi

#Checking for MySQL version 5.5 or greater
MYSQL_VER=$("$_mysql" --version 2>&1 | sed 's/.*Distrib \(.*\)\.\(.*\)\..*,.*/\1\2/; 1q')
if [[ "$MYSQL_VER" -ge 55 ]]; then 
    echo "Success: MySQL is installed and at least version 5.5"
else
    echo "Fail: MySQL needs to be at least 5.5 for the MagicDB app to operate."
    exit 1
fi

echo ""
echo ""

# This may not be necessary anymore, since Spring creates the database at initial runtime. 
# I'll leave it in for now, since it doesn't hurt anything.
# Create Database
# Prompt to create database
echo -n "Do you want to create the MagicDB MySQL database now? [Y]/n:"
read createDB
if [[ ${createDB} == y* ]] || [[ ${createDB} == Y* ]] || [[ ${createDB} == "" ]]; then
    echo "**** Please ensure your MySQL database is running ****"
    # Prompt for database credentials
    # echo "Prompt for credentials"
    # Run create script
    echo "Running Database creation script with default credentials"
    "$_mysql" --host=localhost --user=magicprogram --password=magicpassword --default-character-set=utf8 < ../sql/CreateDatabase.sql
    echo "Success: Created Database (Ignore password warning)"
    "$_mysql" --host=localhost --user=magicprogram --password=magicpassword --default-character-set=utf8 < ../sql/CreateTables.sql
    echo "Success: Created Tables (Ignore password warning)"
else
    echo "**** Database will not be automatically created."
    echo "**** You may need to run the DB scripts located in MagicDBAPI/src/main/scripts against your local MySQL Database."
fi
echo ""
echo ""
# Install Parent POM
echo "Installing Parent POM into local Maven repository"
"$_mvn" -f ../../../parent_pom.xml clean install

echo ""
echo ""

# Install API Jar into local Maven Repo
echo "Installing MagicDBAPI Jar into local Maven repository."
# echo "Skipping tests at this time, due to external dependencies."
"$_mvn" -f ../../../pom.xml clean install

echo ""
echo ""

# Download MagicDBUpdater project
echo "Downloading MagicDBUpdater from Git."
# echo "Changing directory to parent project folder."
cd ../../../../
echo -n "Please input your Bitbucket username [cfebles2]:"
read bitbucketUser
if [[ ${bitbucketUser} == "" ]]; then
    bitbucketUser="cfebles2"
fi
git clone https://${bitbucketUser}@bitbucket.org/cfebles2/magicdbupdater.git
# Build MagicDbUpdater project
echo "Building MagicDbUpdater Jar."
# echo "Skipping tests at this time, due to external dependencies."
cd magicdbupdater
"$_mvn" clean package
# Prompt to run MagicDBUpdater --populateDatabase
echo -n "Do you want to populate the MagicDB MySQL database now? This process can take over an hour, depending on your machine [Y]/n:"
read populateDB
if [[ ${populateDB} == y* ]] || [[ ${populateDB} == Y* ]] || [[ ${populateDB} == "" ]]; then
    cd target
    ./MagicDBUpdater.sh -populateDatabase
    cd ..
fi

# Download MagicDBWebApp project
echo "Downloading MagicDBWebApp from Git."
# echo "Changing directory to parent project folder."
cd ..
git clone https://${bitbucketUser}@bitbucket.org/cfebles2/magicdbwebapp.git
# Build MagicDbWebApp project
echo "Building MagicDBWebApp War."
# echo "Skipping tests at this time, due to external dependencies."
cd magicdbwebapp
"$_mvn" clean package
echo "Deploy magicdbwebapp/target/MagicDBWebApp.war into your web container (e.g. Tomcat)."

echo ""
echo "If everything completed without error, you should be ready to work on this app."
echo "Unit tests should pass now. You can run them within your IDE, or from the command line with JUnit."
echo "Success: Done."
