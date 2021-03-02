package ie.ucd.COMP47660GP.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "creditcards")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private int id;
    //@Column(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    @Column(name = "card_num", unique = true, nullable = false)
    private String cardNum;
    @Column(name = "card_name")
    private String name;
    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;
    @Column(name = "cvv")
    private String securityCode;

    public CreditCard(int id, User user, String cardNum, String name, LocalDateTime expiryDate, String securityCode) {
        // ID auto gen
        this.user = user;
        this.cardNum = cardNum;
        this.name = name;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
    }

    public CreditCard() {
        this.user = new User("Mr test", "test", "test113", "testidge", "test");
        this.cardNum = "test";
        this.name = "test";
        this.expiryDate = LocalDateTime.now();
        this.securityCode = "test";
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

    public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

}