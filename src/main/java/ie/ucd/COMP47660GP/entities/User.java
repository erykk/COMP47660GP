package ie.ucd.COMP47660GP.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table( name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_num")
    private String phoneNum;
    private Boolean exec;

    public User(String firstName,String lastName, String email, String address, String phoneNum) {
        // ID auto generated
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNum = phoneNum;
    }

    public User() {
        this.firstName = "test";
        this.lastName = "test";
        this.email = "test";
        this.address = "test";
        this.phoneNum = "test";
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
}