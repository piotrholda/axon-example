package piotrholda.axonexample.query;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import piotrholda.axonexample.FlightRescheduledEvent;
import piotrholda.axonexample.FlightScheduledEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AllFlightsProjection {

    private final Map<String, FlightSchedule> flightSchedules = new ConcurrentHashMap<>();

    @EventHandler
    public void on(FlightScheduledEvent event) {
        String flightId = event.getFlightId();
        flightSchedules.put(flightId, new FlightSchedule(flightId, event.getGateNumber()));
    }

    @EventHandler
    public void on(FlightRescheduledEvent event) {
        flightSchedules.get(event.getFlightId()).setGateNumber(event.getGateNumber());
    }

    @QueryHandler
    public List<FlightSchedule> handle(FindAllFlightsQuery query) {
        return new ArrayList<>(flightSchedules.values());
    }
}
