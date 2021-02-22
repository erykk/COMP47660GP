use comp47660;

drop table flights;
drop table users;
drop table reservations;


create table flights(
    id INT NOT NULL AUTO_INCREMENT, 
    flightSource VARCHAR(20) NOT NULL, 
    destination VARCHAR(20) NOT NULL,
    flightTime VARCHAR(20) NOT NULL, 
    flightNum VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)); 


INSERT INTO flights(id, flightSource, destination, flightTime, flightNum) VALUES (1,"Dublin", "London", "20-02-21, 14:30", "FR587L");

INSERT INTO flights(id, flightSource, destination, flightTime, flightNum) VALUES (2,"Dublin", "Paris", "20-02-21, 9:00", "FR827P");

INSERT INTO flights(id, flightSource, destination, flightTime, flightNum) VALUES (3,"Dublin", "Madrid", "20-02-21, 17:30", "FR256M");

INSERT INTO flights(id, flightSource, destination, flightTime, flightNum) VALUES (4,"Dublin", "New York", "20-02-21, 8:30", "FR927NY");




create table users( 
    id INT NOT NULL AUTO_INCREMENT, 
    first_name VARCHAR(20) NOT NULL, 
    last_name VARCHAR(20) NOT NULL, 
    email VARCHAR(45) NOT NULL,
    address VARCHAR(20) NOT NULL,
    phoneNum VARCHAR(20) NOT NULL,
    is_exec VARCHAR(5) NOT NULL,
    PRIMARY KEY (id)); 

INSERT INTO users(first_name, last_name, email, address, phoneNum, is_exec) 
    VALUES ("Jacob", "Hodson", "jacobhodson@gmail.com", "Dublin", "0861234567", "false");

INSERT INTO users(first_name, last_name, email, address, phoneNum, is_exec)    
    VALUES ("Collette", "Krueger", "collettekrueger@gmail.com", "Dublin", "0867654321", "true");

INSERT INTO users(first_name, last_name, email, address, phoneNum, is_exec)
    VALUES ("Caroline", "Sadler", "carolinesadler@gmail.com", "Dublin", "0830183759", "false");

INSERT INTO users(first_name, last_name, email, address, phoneNum, is_exec)
    VALUES ("Vinny", "Mcfarland", "vinnymcfarland@gmail.com", "Dublin", "0858301423", "true");
    
    INSERT INTO users(first_name, last_name, email, address, phoneNum, is_exec) 
    VALUES ("Mason", " Wilkins", "masonwilkins@gmail.com", "Dublin", "0866528462", "false");

INSERT INTO users(first_name, last_name, email, address, phoneNum, is_exec)    
    VALUES ("Tom", "O'Brien", "tomobrien@gmail.com", "Dublin", "0827264853", "true");

INSERT INTO users(first_name, last_name, email, address, phoneNum, is_exec)
    VALUES ("Benedict", "Fischer", "benedictfischer@gmail.com", "Dublin", "0830293847", "false");

INSERT INTO users(first_name, last_name, email, address, phoneNum, is_exec)
    VALUES ("Karan", "Newton", "karannewton@gmail.com", "Dublin", "0851100228", "true");





create table reservations( 
    id_flight INT NOT NULL, 
    id_user INT NOT NULL, 
    cancelled VARCHAR(5) NOT NULL,
    PRIMARY KEY (id_flight, id_user));


INSERT INTO reservations(id_flight, id_user, cancelled) VALUES (1,1, "false");

INSERT INTO reservations(id_flight, id_user, cancelled) VALUES (4,4, "false");

INSERT INTO reservations(id_flight, id_user, cancelled) VALUES (2,3, "false");

INSERT INTO reservations(id_flight, id_user, cancelled) VALUES (3,2, "false");

INSERT INTO reservations(id_flight, id_user, cancelled) VALUES (1,5, "false");

INSERT INTO reservations(id_flight, id_user, cancelled) VALUES (4,8, "true");

INSERT INTO reservations(id_flight, id_user, cancelled) VALUES (2,7, "false");

INSERT INTO reservations(id_flight, id_user, cancelled) VALUES (3,6, "false");
