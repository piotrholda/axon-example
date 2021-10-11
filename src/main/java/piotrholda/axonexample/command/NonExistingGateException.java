package piotrholda.axonexample.command;

public class NonExistingGateException extends RuntimeException {

    private final String gateNumber;

    public NonExistingGateException(String gateNumber) {
        this.gateNumber = gateNumber;
    }
}
