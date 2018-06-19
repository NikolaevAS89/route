package ru.timestop.route.trace.exception;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 17.06.2018
 */
public class PointsNotFoundException extends TraceServiceException {
    public PointsNotFoundException(Integer pointId) {
        super(String.format("Points of route %d not found", pointId));
    }
}
