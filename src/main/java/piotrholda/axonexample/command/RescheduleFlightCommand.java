package piotrholda.axonexample.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RescheduleFlightCommand {

    @TargetAggregateIdentifier
    private final String flightId;
    private final String gateNumber;

    public RescheduleFlightCommand(String flightId, String gateNumber) {
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
