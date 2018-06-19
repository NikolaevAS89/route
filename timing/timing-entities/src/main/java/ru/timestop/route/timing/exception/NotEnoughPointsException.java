package ru.timestop.route.timing.exception;

import ru.timestop.route.timing.exception.TimingServiceException;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 03.07.2018
 */
public class NotEnoughPointsException extends TimingServiceException {
    public NotEnoughPointsException() {
        super("No enoth points in route service");
    }
}
