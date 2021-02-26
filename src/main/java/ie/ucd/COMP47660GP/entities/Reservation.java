package ie.ucd.COMP47660GP.entities;

import javax.persistence.*;


@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id")
    private int id;
    //@Column(name = "flight_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;
    //@Column(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "cancelled")
    private Boolean cancelled;

    public Reservation(int id, Flight flight, User user) {
        // ID auto gen
        this.flight = flight;
        this.user = user;
        this.cancelled = false;
    }

    public Reservation() {
        this.flight = new Flight();
        this.user = new User();
        this.cancelled = false;
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

    public Boolean getCancelled() { return this.cancelled; }

    public void setCancelled(Boolean status) { this.cancelled = status; }
}