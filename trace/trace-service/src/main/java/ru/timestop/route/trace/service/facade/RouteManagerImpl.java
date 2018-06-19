package ru.timestop.route.trace.service.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.timestop.route.trace.entities.Point;
import ru.timestop.route.trace.entities.Route;
import ru.timestop.route.trace.entities.Schedule;
import ru.timestop.route.trace.exception.NotEnoughPointsException;
import ru.timestop.route.trace.exception.PointsNotFoundException;
import ru.timestop.route.trace.exception.RouteNotFoundException;
import ru.timestop.route.trace.service.controll.PointService;
import ru.timestop.route.trace.service.controll.RouteService;
import ru.timestop.utilites.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 17.06.2018
 */
@Service
@Qualifier("routeManager")
public class RouteManagerImpl implements RouteManager {

    private static final Logger LOG = LoggerFactory.getLogger(RouteManagerImpl.class);

    @Autowired
    @Qualifier("RouteService")
    private RouteService routeService;

    @Autowired
    @Qualifier("TraceService")
    private PointService pointService;

    public Route createRoute(List<Integer> pointIds) throws NotEnoughPointsException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("createRoute(" + JsonUtil.toJson(pointIds) + ")");
        }
        return createRoute(Boolean.FALSE, -1, pointIds);
    }

    @Override
    public Route createRoute(Integer time, List<Integer> pointIds) throws NotEnoughPointsException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("createRoute(" + time + "," + JsonUtil.toJson(pointIds) + ")");
        }
        return createRoute(Boolean.TRUE, time, pointIds);
    }

    @Override
    public List<Schedule> getSchedules() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("getSchedules()");
        }
        long elapse = System.nanoTime();
        List<Route> routes = routeService.getRoutes();
        List<Schedule> result = new ArrayList<>(routes.size());
        for (Route route : routes) {
            List<Point> points = pointService.getRoutePoints(route);
            result.add(new Schedule.ScheduleBuilder()
                    .setPoints(points)
                    .setRoute(route)
                    .build());
        }
        elapse -= System.nanoTime();
        if (LOG.isDebugEnabled()) {
            LOG.debug("getSchedules() time : " + elapse);
        }
        return result;
    }

    @Override
    public Schedule getSchedule(Integer routeId) throws RouteNotFoundException, PointsNotFoundException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("getSchedule(" + routeId + ")");
        }
        Route route;
        try {
            route = routeService.getRoute(routeId);
        } catch (EmptyResultDataAccessException e) {
            throw new RouteNotFoundException(routeId);
        }
        List<Point> points = pointService.getRoutePoints(route);
        if (points.size() == 0) {
            throw new PointsNotFoundException(routeId);
        }
        return new Schedule.ScheduleBuilder()
                .setPoints(points)
                .setRoute(route)
                .build();
    }

    /**
     * create new {@code Route} object
     *
     * @param isReady  value of field
     * @param time     value of field
     * @param pointIds value of field
     * @return new route object
     */
    private Route createRoute(Boolean isReady, Integer time, List<Integer> pointIds) throws NotEnoughPointsException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("createRoute(" + isReady + ", " + time + ", " + JsonUtil.toJson(pointIds) + ")");
        }
        if (pointIds == null || pointIds.size() < 2) {
            throw new NotEnoughPointsException();
        }
        Route route = routeService.createRoute(isReady, time);
        pointService.registerPoints(route, pointIds);
        return route;
    }
}
