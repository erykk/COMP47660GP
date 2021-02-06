package src.main.java.entites;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {
    private int id;
    private String source;
    private String destination;
    private LocalDateTime time;
    private String flightNum;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy h:mma");

    public Flight(int id, String source, String destination, String timeString, String flightNum) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.time = LocalDateTime.parse(timeString, formatter);
        this.flightNum = flightNum;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
        this.id = id;
    }
    
    public String getSource() {
		return source;
	}

	public void setSource(String source) {
        this.source = source;
    }
    
    public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
        this.time = time;
    }
    
    public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }
}