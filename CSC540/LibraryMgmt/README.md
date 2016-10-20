# LibraryManagement
## Library Management System for CSC 540

LibraryManagement is a Libary Management System designed for NCSU for the CSC 540 class.

## Build
### Requirements
LibraryManagament requrires that your have Java 1.6 JDK and Maven installed on your system to build it.

### Setting up Oracle with Maven
Oracle does not keep their driver file on a public Maven repo, so you will have to manually install it.

1. You will need to obtain the oracle jdbc jar.  You can get the jar file from [Oracle's Download Page](http://www.oracle.com/technetwork/apps-tech/jdbc-112010-090769.html) or from AFS.  I found the file 'ojdbc14.jar' on AFS to use.  NOTE: You will need to find the JDBC that matches with version 11.2.0.1.0
2. You will need to install the jdbc jar into your mvn repo.  Go to the home folder for this project.  I placed the 'ojdbc14.jar' file in this home folder to make installation easier.  Then use the following code to install the jdbc jar file `mvn install:install-file -Dfile=ojdbc14.jar -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=11.2.0.1.0 -Dpackaging=jar`

### Setting up Oracle Database Credentials
You will have to create a file containing your database credentials for use with the NCSU Oracle system.  These credentials were given to you for CSC 540 and are what you use to log into sqlplus.

1. Create the file '/src/main/resources/jdbc.properties' in the LibraryManagement project.
2. Add the following lines of code to this file 
```
# This file will contain your NCSU oracle DB credentials.
oracle.username=REPLACE_THIS_WITH_YOUR_USERNAME
oracle.password=REPLACE_THIS_WITH_YOUR_PASSWORD
```
3. Replace the capitalized instruction spots with your credentials.

### Building LibraryManagement
1. Run the following on your command line `mvn clean package`
2. If sucsessful, you should have generated a file called 'target/LibraryManagement.jar'.  You should be able to run this file by running the following command: `java -jar LibraryManagement.jar`
