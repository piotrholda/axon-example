package piotrholda.axonexample;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import piotrholda.axonexample.command.Flight;
import piotrholda.axonexample.command.RescheduleFlightCommand;
import piotrholda.axonexample.command.ScheduleFlightCommand;

class FlightTest {

    private FixtureConfiguration<Flight> flightFixture;

    @BeforeEach
    void setUp() {
        flightFixture = new AggregateTestFixture<>(Flight.class);
    }

    @Test
    void shouldScheduleNewFlight() {
        String flightId = "AM1";
        String gateNumber = "A01";

        flightFixture.givenNoPriorActivity()
                .when(new ScheduleFlightCommand(flightId, gateNumber))
                .expectEvents(new FlightScheduledEvent(flightId, gateNumber));
    }

    @Test
    void shouldRescheduleNewFlight() {
        String flightId = "AM1";
        String gateNumber1 = "A01";
        String gateNumber2 = "A02";

        flightFixture.given(new FlightScheduledEvent(flightId, gateNumber1))
                .when(new RescheduleFlightCommand(flightId, gateNumber2))
                .expectEvents(new FlightRescheduledEvent(flightId, gateNumber2));
    }
}