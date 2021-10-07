package piotrholda.axonexample;


import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ScheduleFlightCommand {

    @TargetAggregateIdentifier
    private final String flightId;

    public ScheduleFlightCommand(String flightId) {
        this.flightId = flightId;
    }

    public String getFlightId() {
        return flightId;
    }

}
