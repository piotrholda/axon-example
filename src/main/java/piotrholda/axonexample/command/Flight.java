package piotrholda.axonexample.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import piotrholda.axonexample.FlightRescheduledEvent;
import piotrholda.axonexample.FlightScheduledEvent;

import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Flight {

//    @Autowired
//    private QueryUpdateEmitter queryUpdateEmitter;

    private static final List<String> EXISTING_GATES = List.of("A01", "A02", "B01", "B02");

    @AggregateIdentifier
    private String flightId;

    private String gateNumber;

    @AggregateMember
    private SeatMap seatMap;

    @AggregateMember
    private List<Leg> legs;

    @CommandHandler
    public Flight(ScheduleFlightCommand command) {
        if (!EXISTING_GATES.contains(command.getGateNumber())) {
            throw new NonExistingGateException(command.getGateNumber());
        }
        apply(new FlightScheduledEvent(command.getFlightId(), command.getGateNumber()));
    }

    protected Flight() {

    }

    @CommandHandler
    public void handle(RescheduleFlightCommand command) {
        if (!EXISTING_GATES.contains(command.getGateNumber())) {
            throw new NonExistingGateException(command.getGateNumber());
        }
        if (!gateNumber.equals(command.getGateNumber())) {
            apply(new FlightRescheduledEvent(command.getFlightId(), command.getGateNumber()));
        }
    }

    @EventSourcingHandler
    public void on(FlightScheduledEvent event) {
        flightId = event.getFlightId();
        gateNumber = event.getGateNumber();
/*
        queryUpdateEmitter.emit(ScheduleForBookingQuery.class,
                query -> query.getBookingId().equals(event.getFlightId()),
                new FlightScheduleUpdate(event.getFlightId()));
*/
    }

    @EventSourcingHandler
    public void on(FlightRescheduledEvent event) {
        gateNumber = event.getGateNumber();
    }
}
