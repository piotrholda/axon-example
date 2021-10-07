package piotrholda.axonexample;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Flight {

    @AggregateIdentifier
    private String flightId;

    @AggregateMember
    private SeatMap seatMap;

    @AggregateMember
    private List<Leg> legs;

    @CommandHandler
    public void handle(ScheduleFlightCommand command) {
        apply(new FlightScheduledEvent(command.getFlightId()));
    }
}
