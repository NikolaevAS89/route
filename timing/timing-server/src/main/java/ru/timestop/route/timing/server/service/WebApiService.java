package ru.timestop.route.timing.server.service;

import ru.timestop.exceptions.WebApiException;

import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 12.08.2018
 */
public interface WebApiService {

    /**
     * @param points id of points
     * @return calculated time of trace or -1 if trace not exists
     */
    Integer calcTraceTime(List<Integer> points) throws WebApiException;

}
