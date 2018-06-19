package ru.timestop.route.timing.exception;

import ru.timestop.route.timing.exception.TimingServiceException;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 06.08.2018.
 */
public class EdgeNotFoundException extends TimingServiceException {
    public EdgeNotFoundException(Integer pointOne, Integer pointTwo) {
        super(String.format("Edge (%d, %d) not found", pointOne, pointTwo));
    }
}
