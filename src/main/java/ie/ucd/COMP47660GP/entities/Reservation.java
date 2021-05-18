package ie.ucd.COMP47660GP.entities;

import javax.persistence.*;


@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id")
    private int id;
    @OneToOne(fetch = FetchType.EAGER)
    private Flight flight;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    @Column(name = "cancelled")
    private Boolean cancelled;

    private Long userID;

    public Reservation(Flight flight, User user) {
        // ID auto gen
        this.flight = flight;
        this.user = user;
        this.cancelled = false;
    }

    public Reservation() {
        this.flight = new Flight();
        this.user = new User("Mr test", "test", "test113", "testidge", "test","usertest", "Pass123(", "Pass123(");
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

    public int getReservation_id() {
        return id;
    }

    public Long getUserID(){ return this.userID;}

    public void setUserID(Long userID){ this.userID = userID; }
}