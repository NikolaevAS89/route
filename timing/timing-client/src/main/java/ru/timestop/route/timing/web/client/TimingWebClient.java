package ru.timestop.route.timing.web.client;

import java.io.IOException;
import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 23.07.2018
 */
public interface TimingWebClient {

    /**
     * @param pointIds of points trace
     * @return time of points trace
     * @throws IOException
     */
    Integer calcTiming(List<Integer> pointIds) throws IOException;
}