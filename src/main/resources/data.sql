INSERT INTO flights (flight_id, source, destination, date_time, flight_num)
    VALUES
           (1,"Dublin", "London", "21-05-21, 14:30", "FR587L"),
           (2,"Dublin", "Paris", "21-05-21, 9:00", "FR827P"),
           (3,"Dublin", "Madrid", "21-05-21, 17:30", "FR256M"),
           (4,"Dublin", "New York", "21-05-21, 8:30", "FR927NY");


INSERT INTO users(id,  first_name, last_name, email, address, phone_num, exec)
    VALUES (11, "Jacob", "Hodson", "jacobhodson@gmail.com", "Dublin", "0861234567", false),
           (12, "Collette", "Krueger", "collettekrueger@gmail.com", "Dublin", "0867654321", true),
           (13, "Caroline", "Sadler", "carolinesadler@gmail.com", "Dublin", "0830183759", false),
           (14, "Vinny", "Mcfarland", "vinnymcfarland@gmail.com", "Dublin", "0858301423", true),
           (15, "Mason", " Wilkins", "masonwilkins@gmail.com", "Dublin", "0866528462", false),
           (16, "Tom", "O'Brien", "tomobrien@gmail.com", "Dublin", "0827264853", true),
           (17, "Benedict", "Fischer", "benedictfischer@gmail.com", "Dublin", "0830293847", false),
           (18, "Karan", "Newton", "karannewton@gmail.com", "Dublin", "0851100228", true);


INSERT INTO reservations(reservation_id, flight_flight_id, user_id, cancelled)
    VALUES (1, 1,11, false),
           (2, 4,14, false),
           (3, 2,13, false),
           (4, 3,12, false),
           (5, 1,15, false),
           (6, 4,18, true),
           (7, 2,17, false),
           (8, 3,16, false);

UPDATE hibernate_sequence
SET next_val = 100
WHERE next_val < 100