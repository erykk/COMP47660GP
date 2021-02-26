package ie.ucd.COMP47660GP.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import org.springframework.data.annotation.Transient;

import java.util.Collection;
import java.util.Set;

@Entity
@Table( name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "user_id")
    private int id;
    //@Column(name = "first_name")
    private String firstName;
    //@Column(name = "last_name")
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    //@Column(name = "address")
    private String address;
    //@Column(name = "phone_num")
    private String phoneNum;
    //@Column(name = "is_exec")
    private Boolean exec;

    private String password;
    @Transient
    private String verifyPassword;

    @ManyToMany
    private Collection<Role> roles;


    /*
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

    */

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}