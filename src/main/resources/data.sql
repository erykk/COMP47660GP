-- This config doesn't work

use db;


INSERT INTO flights (flight_id, source, destination, date_time, flight_num)
    VALUES
           (1,"Dublin", "London", "21-05-21, 14:30", "FR587L"),
           (2,"Dublin", "Paris", "21-05-21, 9:00", "FR827P"),
           (3,"Dublin", "Madrid", "21-05-21, 17:30", "FR256M"),
           (4,"Dublin", "New York", "21-05-21, 8:30", "FR927NY");


INSERT INTO users(user_id,  first_name, last_name, email, address, phone_num, is_exec)
    VALUES (1, "Jacob", "Hodson", "jacobhodson@gmail.com", "Dublin", "0861234567", false),
           (2, "Collette", "Krueger", "collettekrueger@gmail.com", "Dublin", "0867654321", true),
           (3, "Caroline", "Sadler", "carolinesadler@gmail.com", "Dublin", "0830183759", false),
           (4, "Vinny", "Mcfarland", "vinnymcfarland@gmail.com", "Dublin", "0858301423", true),
           (5, "Mason", " Wilkins", "masonwilkins@gmail.com", "Dublin", "0866528462", false),
           (6, "Tom", "O'Brien", "tomobrien@gmail.com", "Dublin", "0827264853", true),
           (7, "Benedict", "Fischer", "benedictfischer@gmail.com", "Dublin", "0830293847", false),
           (8, "Karan", "Newton", "karannewton@gmail.com", "Dublin", "0851100228", true);


INSERT INTO reservations(reservation_id, flight_id, user_id, cancelled)
    VALUES (1, 1,1, false),
           (2, 4,4, false),
           (3, 2,3, false),
           (4, 3,2, false),
           (5, 1,5, false),
           (6, 4,8, true),
           (7, 2,7, false),
           (8, 3,6, false);
