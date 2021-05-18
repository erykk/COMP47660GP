INSERT INTO flights (flight_id, source, destination, date_time, flight_num, date, time)
    VALUES
           (1,"Dublin", "London", "2021-05-21, 14:30", "FR587L", "2021-05-21", "14:30"),
           (2,"Dublin", "Paris", "2021-05-21, 09:00", "FR827P", "2021-05-21", "09:00"),
           (3,"Dublin", "Madrid", "2021-05-21, 17:30", "FR256M", "2021-05-21", "17:30"),
           (4,"Dublin", "New York", "2021-05-21, 08:30", "FR927NY", "2021-05-21", "08:30");


INSERT INTO users(id,  first_name, last_name, email, address, phone_num, exec, username)
    VALUES (11, "Jacob", "Hodson", "jacobhodson@gmail.com", "Dublin", "0861234567", false, "user123_"),
           (12, "Collette", "Krueger", "collettekrueger@gmail.com", "Dublin", "0867654321", true, "user124_"),
           (13, "Caroline", "Sadler", "carolinesadler@gmail.com", "Dublin", "0830183759", false, "user125_"),
           (14, "Vinny", "Mcfarland", "vinnymcfarland@gmail.com", "Dublin", "0858301423", true, "user126_"),
           (15, "Mason", " Wilkins", "masonwilkins@gmail.com", "Dublin", "0866528462", false, "user127_"),
           (16, "Tom", "O'Brien", "tomobrien@gmail.com", "Dublin", "0827264853", true, "user128_"),
           (17, "Benedict", "Fischer", "benedictfischer@gmail.com", "Dublin", "0830293847", false, "user129_"),
           (18, "Karan", "Newton", "karannewton@gmail.com", "Dublin", "0851100228", true, "user120_");


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