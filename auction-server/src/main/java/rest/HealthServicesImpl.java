package rest;

/**
 * Health check REST service implementation.
 * Handles request-response operations for health check
 *
 * Author: Ido Barash
 */
public class HealthServicesImpl implements HealthServices {

    @Override
    public boolean checkHealth() {
        return true;
    }
}
