package piotrholda.axonexample.command;


import org.axonframework.commandhandling.RoutingKey;

public class ScheduleFlightCommand {

    @RoutingKey
    private final String flightId;
    private final String gateNumber;

    public ScheduleFlightCommand(String flightId, String gateNumber) {
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
