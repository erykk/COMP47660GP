COMP47660 Group Project

Instructions:
To run the service run the following commands:

Package the application:
```
mvn package
```
Run docker-compose.yml which starts the mysql server. Wait until "ready for 
connections" is logged before starting the application, which should take about
a minute. To shutdown the mysql server, press CTRL + C.
```
docker-compose up
```
Run the application:
```
mvn spring-boot:run
```
On the first run of the application, it will throw exceptions on startup as 
its tables do not exist and will subsequently create them.


Notes:
Database data is stored in the ./data directory and persists between DB 
shutdowns.