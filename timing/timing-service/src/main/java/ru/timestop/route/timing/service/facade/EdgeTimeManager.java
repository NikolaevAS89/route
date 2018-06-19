package ru.timestop.route.timing.service.facade;

import ru.timestop.route.timing.exception.EdgeNotFoundException;
import ru.timestop.route.timing.exception.NotEnoughPointsException;

import java.util.List;

/**
 * facade of route-service-edges module logic.
 *
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 02.07.2018
 */
public interface EdgeTimeManager {

    /**
     * @param points id of points
     * @return calculated time of trace
     * @throws NotEnoughPointsException if points less then 2
     * @throws EdgeNotFoundException    if one or more part not exists
     */
    Integer calcTraceTime(List<Integer> points) throws NotEnoughPointsException, EdgeNotFoundException;
}