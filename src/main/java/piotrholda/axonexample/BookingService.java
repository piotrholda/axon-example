package piotrholda.axonexample;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Component
public class BookingService {

    @Autowired
    private QueryGateway queryGateway;

    public CompletableFuture<FlightSchedule> scheduleForBooking(ScheduleForBookingQuery query) {
        //Stream<FlightSchedule> rStream = queryGateway.scatterGather(query, ResponseTypes.instanceOf(FlightSchedule.class), 500, TimeUnit.MILLISECONDS);

        //SubscriptionQueryResult<FlightSchedule, FlightScheduleUpdate> result = queryGateway.subscriptionQuery(query, FlightSchedule.class, FlightScheduleUpdate.class);
        //result.handle(initialResult -> {}, updateResult -> {});
        //result.initialResult().doOnNext(initialResult -> {}).subscribe();
        //result.updates().doOnNext(updateResult -> {}).subscribe();
        //result.updates().doOnComplete(() -> {});
/*
        result.initialResult()
                .concatWith(result.updates())
                .doOnNext(flightSchedule -> {})
                .subscribe();
*/
        //result.close();
        return queryGateway.query(query, FlightSchedule.class);
    }
}
