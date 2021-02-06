package src.main.java.entites;

import src.main.java.entites.User;

public class CreditCard {
    private int id;
    private User user;
    private String cardNum;
    private String name;
    private String expiryDate;
    private String securityCode;

    public CreditCard(int id, User user, String cardNum, String name, String expiryDate, String securityCode) {
        this.id = id;
        this.user = user;
        this.cardNum = cardNum;
        this.name = name;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
        this.id = id;
    }

    public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
        this.name = name;
    }

    public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

}