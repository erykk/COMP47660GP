package ie.ucd.COMP47660GP.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import ie.ucd.COMP47660GP.service.AttributeEncryptor;

import javax.persistence.*;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Entity
@Table(name = "creditcards")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private int id;
    // @Column(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "card_num", unique = false, nullable = false)
    @Convert(converter = AttributeEncryptor.class)
    private String cardNum;
    @Column(name = "card_name")
    @Convert(converter = AttributeEncryptor.class)
    private String name;
    @Column(name = "expiry_date")
    @Convert(converter = AttributeEncryptor.class)
    private String expiryDate;
    @Column(name = "cvv")
    @Convert(converter = AttributeEncryptor.class)
    private String securityCode;

    public CreditCard(int id, User user, String cardNum, String name, String expiryDate, String securityCode) {
        // ID auto gen
        this.user = user;
        this.cardNum = cardNum;
        this.name = name;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
    }

    public CreditCard() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String toString(){
        return "************" + (cardNum.length() >= 15 ? cardNum.substring(11,15) : "****");
    }

}