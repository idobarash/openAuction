package exception;

/**
 * A general exception class
 * Handles all auction server exceptions.
 *
 * Author: Ido Barash
 */
public class AuctionException extends RuntimeException {
    public AuctionException(String message) {
        super(message);
    }
}
