/** Exception for trying to splice a list into itself */
public class SelfInsertException extends RuntimeException {
    /** default constructor */
    public SelfInsertException(String message) {
        super();
        System.err.println(message);
    }
}
