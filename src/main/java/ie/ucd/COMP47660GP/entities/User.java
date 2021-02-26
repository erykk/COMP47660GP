package ie.ucd.COMP47660GP.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table( name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNum;
    private Boolean exec;

    //@OneToMany
    //private List<Reservation> reservations;

    public User(String firstName,String lastName, String email, String address, String phoneNum) {
        // ID auto generated
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNum = phoneNum;
        this.exec = false;
    }

    public User() {
        this.firstName = "test";
        this.lastName = "test";
        this.email = "test";
        this.address = "test";
        this.phoneNum = "test";
        this.exec = false;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Boolean getExec() {
        return exec;
    }

    public void setExec(Boolean exec) {
        this.exec = exec;
    }
}