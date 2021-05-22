package ie.ucd.COMP47660GP.entities;

import javax.persistence.*;
import java.util.Collection;

import ie.ucd.COMP47660GP.service.AttributeEncryptor;

@Entity
@Table( name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotNull
    @Convert(converter = AttributeEncryptor.class)
    private String firstName;
    //@NotBlank
    @Convert(converter = AttributeEncryptor.class)
    private String lastName;
    //@Email
    private String email;
    //@Min(min = 3, max = 60)
    @Convert(converter = AttributeEncryptor.class)
    private String address;
    //@Min(min = 3, max = 60)
    @Convert(converter = AttributeEncryptor.class)
    private String phoneNum;
    private Boolean exec;
    private String password;
    @Transient
    private String verifyPassword;

    // Assignment 3
    @Transient
    private String passwordConfirm;
    // Assignment 3
    private String username;
    private String userRole;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;


    public User(String firstName, String lastName, String email, String address, String phoneNum, String username, String password, String passwordConfirm) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNum = phoneNum;
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public User() { }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

    /**
     *   Assignment 3 Methods
      */
    public String getRole(){ return userRole;}

    public void setRole(String role){
        this.userRole = role;
    }

    public String getUsername(){ return username;}

    public void setUsername(String username){
        this.username = username;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}