INSERT INTO flights (flight_id, source, destination, date_time, flight_num, date, time)
    VALUES
           (1,"Dublin", "London", "2021-07-21 14:30", "FR587L", "2021-07-21", "14:30"),
           (2,"Dublin", "Paris", "2021-07-21 09:00", "FR827P", "2021-07-21", "09:00"),
           (3,"Dublin", "Madrid", "2021-07-21 17:30", "FR256M", "2021-07-21", "17:30"),
           (4,"Dublin", "New York", "2021-07-21 08:30", "FR927NY", "2021-07-21", "08:30");

UPDATE hibernate_sequence
SET next_val = 100
WHERE next_val < 100