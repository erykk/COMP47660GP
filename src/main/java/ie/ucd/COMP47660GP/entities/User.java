package src.main.java.entites;

public class User {
    private int id;
    private String name;
    private String email;
    private String address;
    private String phoneNum;
    private Boolean exec;

    public User(int id, String name, String email, String address, String phoneNum) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNum = phoneNum;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
        this.id = id;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
        this.name = name;
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