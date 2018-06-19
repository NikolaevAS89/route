package ru.timestop.route.timing.exception;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 06.08.2018.
 */
public class TimingServiceException extends Exception {
    public TimingServiceException(String msg) {
        super(msg);
    }

    public TimingServiceException() {
        super();
    }

    public TimingServiceException(Throwable e) {
        super(e);
    }
}