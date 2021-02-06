package src.main.java.entites;

import src.main.java.entites.Flight;
import src.main.java.entites.User;

public class Reservation {
    private int id;
    private Flight flight;
    private User user;
    private Boolean cancelled;

    public Reservation(int id, Flight flight, User user) {
        this.id = id;
        this.flight = flight;
        this.user = user;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
        this.id = id;
    }

    public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
        this.user = user;
    }
}