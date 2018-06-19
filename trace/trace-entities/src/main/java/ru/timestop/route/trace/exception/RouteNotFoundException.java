package ru.timestop.route.trace.exception;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 17.06.2018
 */
public class RouteNotFoundException extends TraceServiceException {
    public RouteNotFoundException(Integer routeId) {
        super(String.format("Route with id %d not found", routeId));
    }
}
