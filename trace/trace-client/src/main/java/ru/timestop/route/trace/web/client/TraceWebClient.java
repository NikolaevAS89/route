package ru.timestop.route.trace.web.client;

import ru.timestop.route.trace.entities.Schedule;
import ru.timestop.route.trace.exception.TraceServiceException;

import java.io.IOException;
import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 23.07.2018
 */
public interface TraceWebClient {
    /**
     * @param pointIds
     * @return
     */
    Integer createRoute(List<Integer> pointIds) throws IOException, TraceServiceException;


    /**
     * @return
     */
    List<Schedule> getRoutes() throws IOException, TraceServiceException;

    /**
     * @param routeId
     * @return
     */
    List<Schedule> getRoute(Integer routeId) throws IOException, TraceServiceException;
}