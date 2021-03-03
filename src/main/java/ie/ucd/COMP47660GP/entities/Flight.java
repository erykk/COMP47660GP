package ie.ucd.COMP47660GP.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "flight_id")
    private int id;
    private String source;
    private String destination;
    private LocalDateTime dateTime;
    private String flightNum;

    public Flight(String source, String destination, LocalDateTime dateTime, String flightNum) {
        // ID auto gen
        this.source = source;
        this.destination = destination;
        this.dateTime = dateTime;
        this.flightNum = flightNum;
    }

    public Flight(){
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
    
    public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    
    public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }
}