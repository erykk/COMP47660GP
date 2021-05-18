## COMP47660 Group Project

### Instructions:

The service requires for a MySQL server to be running prior to start up. One way to do this is to start a MySQL instance on port 3306 using XAMPP: https://www.apachefriends.org/index.html

The DBMS username and password **must** be updated in the application.properties file with credentials usable with your local MySQL server.

No secrets are stored in code or configuration files. Jasypt is used to allow for required passwords to be stored in 
configuration files and used by the application. The password to decrypt the secrets is provided as a command line argument
at runtime.

The application will create the database schema and all required tables, and will fill them
with some data. After the first run, the following line in application.properties **must** be
commented out to allow for persistent data storage:

```
    spring.datasource.initialization-mode=always
```

The application can be run by navigating to the application folder and running

```
    mvn spring-boot:run "-Dspring-boot.run.arguments=--jasypt.encryptor.password=solarwinds123"
```

Following initialization, navigate to

```
    https://localhost:8443
```


### Secrets Encryption:
Encrypted secrets stored in configuration files are encrypted using JasyptPBEStringEncryptionCLI from jasypt 1.9.3.
In order to encrypt a secret, you must first navigate to the jasypt directory using the following command:

```
    cd ~\.m2\repository\org\jasypt\jasypt\1.9.3    
```

When in the jasypt directory, run the following command to encrypt sensitive data that needs to be stored in source or 
configuration files, replacing VALUE_TO_BE_ENCRYPTED with the secret:

```
    java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="VALUE_TO_BE_ENCRYPTED" password="solarwinds123" algorithm=PBEWITHHMACSHA512ANDAES_256 ivGeneratorClassName=org.jasypt.iv.RandomIvGenerator
```



### Note:
You may want to add the certificate to the browser's keystore.
If so, add the keystore/solarwinds.p12 file to browser keystore.
