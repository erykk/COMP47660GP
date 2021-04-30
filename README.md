COMP47660 Group Project

Instructions:

The service requires for a MySQL server to be running prior to start up. One way to do this is to start a MySQL instance on port 3306 using XAMPP: https://www.apachefriends.org/index.html

The DBMS username and password **must** be updated in the application.properties file to credentials usable with your local MySQL server.

The application will create the database schema and all required tables, and will fill them
with some data. After the first run, the following line in application.properties **must** be
commented out:

```
    spring.datasource.initialization-mode=always
```


The application can be run by navigating to the application folder and running

```
    mvn spring-boot:run
```

Following initialization, navigate to

```
    https://localhost:8080
```

You may want to add the certificate to the browser's keystore.
If so, add the keystore/solarwinds.p12 file to browser keystore. Password is in 
application.properties file.
