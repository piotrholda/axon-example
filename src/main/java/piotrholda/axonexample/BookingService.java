package piotrholda.axonexample;

import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class BookingService {

    @Autowired
    private QueryGateway queryGateway;

    public CompletableFuture<FlightSchedule> scheduleForBooking(ScheduleForBookingQuery query) {
        return queryGateway.query(query, FlightSchedule.class);
    }
}
