package piotrholda.axonexample.command;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import piotrholda.axonexample.FlightRescheduledEvent;
import piotrholda.axonexample.FlightScheduledEvent;

import java.io.Serializable;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
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
        log.info("ScheduleFlightCommand received for " + command.getFlightId());
        if (!EXISTING_GATES.contains(command.getGateNumber())) {
            throw new NonExistingGateException(command.getGateNumber());
        }
        apply(new FlightScheduledEvent(command.getFlightId(), command.getGateNumber()));
        log.info("FlightScheduledEvent sent for " + command.getFlightId());
    }

    protected Flight() {

    }

    @CommandHandler
    public void handle(RescheduleFlightCommand command) {
        log.info("RescheduleFlightCommand received for " + command.getFlightId());
        if (!EXISTING_GATES.contains(command.getGateNumber())) {
            throw new NonExistingGateException(command.getGateNumber());
        }
        if (!gateNumber.equals(command.getGateNumber())) {
            apply(new FlightRescheduledEvent(command.getFlightId(), command.getGateNumber()));
            log.info("FlightRescheduledEvent sent for " + command.getFlightId());
        }
    }

    @EventSourcingHandler
    public void on(FlightScheduledEvent event) {
        log.info("FlightScheduledEvent received for " + event.getFlightId());
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
        log.info("FlightRescheduledEvent received for " + event.getFlightId());
        gateNumber = event.getGateNumber();
    }
}
