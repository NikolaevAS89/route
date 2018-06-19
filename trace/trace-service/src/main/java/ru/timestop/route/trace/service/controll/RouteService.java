package ru.timestop.route.trace.service.controll;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.timestop.route.trace.entities.Route;

import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 27.07.2018.
 */
public interface RouteService {

    /**
     * create new route row
     *
     * @param isReady true if time is calculated, false otherwise
     * @param time    of this created route
     * @return created route
     */
    Route createRoute(Boolean isReady, Integer time);

    /**
     * @param routeId search route
     * @return finded route
     * @throws EmptyResultDataAccessException if no route was found
     */
    Route getRoute(Integer routeId);

    /**
     * @return all registred routes
     */
    List<Route> getRoutes();
}
