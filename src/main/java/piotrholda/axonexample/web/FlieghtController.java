package piotrholda.axonexample.web;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import piotrholda.axonexample.command.RescheduleFlightCommand;
import piotrholda.axonexample.command.ScheduleFlightCommand;
import piotrholda.axonexample.query.FindAllFlightsQuery;
import piotrholda.axonexample.query.FlightSchedule;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class FlieghtController {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private QueryGateway queryGateway;

    @PostMapping
    public CompletableFuture<Void> scheduleFlight(String flightId, String gateNumber) {
        return commandGateway.send(new ScheduleFlightCommand(flightId, gateNumber));
    }

    @PutMapping
    public CompletableFuture<Void> rescheduleFlight(String flightId, String gateNumber) {
        return commandGateway.send(new RescheduleFlightCommand(flightId, gateNumber));
    }

    @GetMapping
    public CompletableFuture<List<FlightSchedule>> fidAllFlights() {
        return queryGateway.query(new FindAllFlightsQuery(), ResponseTypes.multipleInstancesOf(FlightSchedule.class));
    }
}
