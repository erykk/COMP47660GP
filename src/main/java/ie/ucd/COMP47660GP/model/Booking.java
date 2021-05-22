package ie.ucd.COMP47660GP.model;

import ie.ucd.COMP47660GP.entities.CreditCard;
import ie.ucd.COMP47660GP.entities.User;

import java.util.LinkedList;
import java.util.List;

public class Booking {
    private List<User> users;
    private CreditCard creditCard;
    int flightID;
    String dateStr;
    String savedCard;
    String execUsername;

    public Booking() {
        users = new LinkedList<>();
        creditCard = new CreditCard();
        flightID = 0;
    }

    public Booking(int num) {
        this();
        for(int i = 0; i < num; i++) {
            users.add(new User());
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getSavedCard() {
        return savedCard;
    }

    public void setSavedCard(String savedCard) {
        this.savedCard = savedCard;
    }

    public String getExecUsername(){ return this.execUsername; }

    public void setExecUsername(String execUsername){ this.execUsername = execUsername;}

    public String toString() {
        String op = "";
        op+= "Users: ";
        for (User user: users){
            op+= user.getId() + ", ";
        }
        op+= ". Credit Card: " + creditCard.toString() + ", Flight ID: " + flightID + ", Date: " + dateStr;
        return op;
    }
}
