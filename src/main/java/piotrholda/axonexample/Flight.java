package piotrholda.axonexample;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Predicate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Flight {

//    @Autowired
//    private QueryUpdateEmitter queryUpdateEmitter;

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

    @EventSourcingHandler
    public void on(FlightScheduledEvent event) {
        flightId = event.getFlightId();
/*
        queryUpdateEmitter.emit(ScheduleForBookingQuery.class,
                query -> query.getBookingId().equals(event.getFlightId()),
                new FlightScheduleUpdate(event.getFlightId()));
*/
    }
}
