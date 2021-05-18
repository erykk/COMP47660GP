package ie.ucd.COMP47660GP.model;

import javax.validation.constraints.NotNull;

public class FlightDetails {
    @NotNull(message = "Enter source")
    String source;
    @NotNull(message = "Enter destination")
    String destination;
    @NotNull(message = "Enter date")
    String dateTime;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
