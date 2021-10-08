package piotrholda.axonexample;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.Test;

class FlightTest {

    private FixtureConfiguration<Flight> flightFixture = new AggregateTestFixture<>(Flight.class);

    @Test
    void shouldScheduleNewFlight() {
        String flightId = "AM1";

        flightFixture.given(new FlightScheduledEvent(flightId))
                .when(new ScheduleFlightCommand(flightId))
                .expectEvents(new FlightScheduledEvent(flightId));
    }
}