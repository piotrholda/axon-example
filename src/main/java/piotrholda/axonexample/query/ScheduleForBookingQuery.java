package piotrholda.axonexample.query;

public class ScheduleForBookingQuery {

    private final String bookingId;

    public ScheduleForBookingQuery(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingId() {
        return bookingId;
    }
}
