package piotrholda.axonexample;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightStatusProjection {


    @EventHandler
    public void on(FlightScheduledEvent event) {

    }

/*
    @QueryHandler
    public FlightSchedule handle(ScheduleForBookingQuery query) {
    }
*/

}
