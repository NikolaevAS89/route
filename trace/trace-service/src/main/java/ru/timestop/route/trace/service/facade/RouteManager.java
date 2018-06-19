package ru.timestop.route.trace.service.facade;

import ru.timestop.route.trace.entities.Route;
import ru.timestop.route.trace.entities.Schedule;
import ru.timestop.route.trace.exception.NotEnoughPointsException;
import ru.timestop.route.trace.exception.PointsNotFoundException;
import ru.timestop.route.trace.exception.RouteNotFoundException;

import java.util.List;

/**
 * Facade of service one.
 *
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 16.06.2018
 */
public interface RouteManager {

    /**
     * New route has {@link Route#isReady} as false and
     * {@link Route#time} as -1
     *
     * @param pointIds of trace
     * @return created route
     */
    Route createRoute(List<Integer> pointIds) throws NotEnoughPointsException;

    /**
     * New route has {@link Route#isReady} as true
     *
     * @param time of route
     * @param pointIds of trace
     * @return
     */
    Route createRoute(Integer time, List<Integer> pointIds) throws NotEnoughPointsException;

    /**
     * @param route serched
     * @return shedule of route
     */
    Schedule getSchedule(Integer route) throws RouteNotFoundException, PointsNotFoundException;

    /**
     * @return all shedules
     */
    List<Schedule> getSchedules();
}