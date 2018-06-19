package ru.timestop.route.trace.service.controll;

import ru.timestop.route.trace.entities.Point;
import ru.timestop.route.trace.entities.Route;

import java.util.List;

/**
 * Service for bind points trace with route and search traces
 *
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 17.06.2018
 */
public interface PointService {

    /**
     * Binding {@code points} with {@code route}
     *
     * @param route
     * @param points
     */
    void registerPoints(Route route, List<Integer> points);

    /**
     * @return All registred {@code points} trace ordered by route_id and position
     */
    List<Point> getRoutePoints();

    /**
     * Search {@code points} trace of {@code route}.
     *
     * @param route which points will be search
     * @return list of points. empty returned list if route not found.
     */
    List<Point> getRoutePoints(Route route);
}