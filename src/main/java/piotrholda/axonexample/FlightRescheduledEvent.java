package piotrholda.axonexample;

public class FlightRescheduledEvent {

    private final String flightId;
    private final String gateNumber;

    public FlightRescheduledEvent(String flightId, String gateNumber) {
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
