package piotrholda.axonexample;

public class FlightScheduledEvent {

    private final String flightId;
    private final String gateNumber;

    public FlightScheduledEvent(String flightId, String gateNumber) {
        this.flightId = flightId;
        this.gateNumber = gateNumber;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getGateNumber() {
        return gateNumber;
    }
}
