## COMP47660 Group Project

###  Startup Checklist:
1. Update MySQL credentials in application.properties
2. After first startup, comment out _spring.datasource.initialization-mode=always in application.properties_
3. After first startup, change _create_ to _update_ to make database storage persistent: _spring.jpa.hibernate.ddl-auto=create_

### Instructions:

The service requires for a MySQL server to be running prior to start up. One way to do this is to start a MySQL instance on port 3306 using XAMPP: https://www.apachefriends.org/index.html

The DBMS username and password **MUST** be updated in the application.properties file with credentials usable with your local MySQL server.
It is advised to encrypt the DB user's password rather than store it in plaintext in a config file, but it will work encrypted **or** in plaintext.
If encrypting the DB user's password, it should be encrypted using the steps below and added to application.properties as:
```
    spring.datasource.password=ENC(CIPHER_TEXT)
```

No secrets are stored in code or configuration files. Jasypt is used to allow for required passwords to be stored in 
configuration files and used by the application. The password to decrypt the secrets is provided as a command line argument
at runtime.

The application can be run by navigating to the application folder and running

```
    mvn spring-boot:run "-Dspring-boot.run.arguments=--jasypt.encryptor.password=solarwinds123"
```

Following initialization, navigate to

```
    https://localhost:8443
```

After initialization, the application will have created the database schema and all required tables, and will fill them
with some data. After the first run, the following line in application.properties **MUST** be
commented out to prevent attempts of adding data when the DB data is persistent:

```
    spring.datasource.initialization-mode=always
```
The following line should be changed from _create_ to _update_ to enable persistent data storage:
```
    spring.jpa.hibernate.ddl-auto=create
```

### Admin Credentials:
```
    Username: admin4145_
    Password: adminRule808!
```


### Encrypting Secrets:
Encrypted secrets stored in configuration files are encrypted using JasyptPBEStringEncryptionCLI from jasypt 1.9.3.

Jasypt 1.9.3 is downloaded when dependencies used are downloaded. Run the following command which will compile the 
application and also download the necessary dependencies, including Jasypt:

```
    mvn compile
```

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

### Logging:
The application log is written under "application.log" in the main directory. It contains app initialisation logs as well as logs performed over the course of application functioning.
