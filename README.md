# Multi-User Application with Springboot 

This is our uek project with a springboot backend and a postgres database

## Getting Started

### Dependencies

This project requires you to use the Java JDK Version 11.
Please select the appropriate JDK in the IntelliJ project settings.

### Executing program

You'll need a Docker container running PostgreSQL on port 5432.
If you have not already, you can set up such a docker container using the command:

```
docker run --name postgres-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
```

### Setup
Open the applicaiton in IntelliJ. you should be able to execute the application after IntelliJ has finished indexing all the files and building the gradle project. Build & Run the application (e.g. by clicking the play button next to the projects main method).

the application should start and run now. You should see a message similar to this at the end of a long console log:
```
2021-09-14 14:31:15.056 INFO 27988 --- [ main] com.example.demo.DemoApplication : Started DemoApplication in 4.122 seconds (JVM running for 4.991)
```
If you've set up the project correctly you should be able to access the endpoint ```http://localhost:8080/userprofile/``` after logging in with the following user:

* username: Luca
* password: LuWid

The site should display all the userprofiles

## Help and Common Issues

Any advise for common problems or issues.

* Restart the PostGreSQL container & check the container is running
* Confirm connection to the DB (e.g. in DBeaver)
* Restart IntelliJ & your Spring Boot application

## Authors

Contributors names

ex. Shawn Lacarta  
ex. Matijas Polazarov  
ex. Nuwera Mohammad  

