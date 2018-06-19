package ru.timestop.route.trace.exception;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 16.06.2018
 */
public class NotEnoughPointsException extends TraceServiceException {
    public NotEnoughPointsException() {
        super("More than one points expected");
    }
}
