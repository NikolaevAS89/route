package ru.timestop.route.trace.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.timestop.exceptions.WebApiException;
import ru.timestop.route.timing.web.client.TimingWebClient;
import ru.timestop.route.trace.entities.Schedule;
import ru.timestop.route.trace.exception.NotEnoughPointsException;
import ru.timestop.route.trace.exception.TraceServiceException;
import ru.timestop.route.trace.service.facade.RouteManager;
import ru.timestop.utilites.JsonUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@Qualifier("webApiService")
public class WebApiServiceImpl implements WebApiService {
    private static final Logger LOG = LoggerFactory.getLogger(WebApiServiceImpl.class);
    @Autowired
    @Qualifier("routeManager")
    private RouteManager routeManager;

    @Autowired
    @Qualifier("timingWebClient")
    private TimingWebClient timingWebClient;

    @Override
    public Integer addRoute(List<Integer> points) throws WebApiException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("addRoute(" + JsonUtil.toJson(points) + ")");
        }
        try {
            try {
                Integer time = timingWebClient.calcTiming(points);
                return routeManager.createRoute(time, points).getIdRoute();
            } catch (IOException e) {
                return routeManager.createRoute(points).getIdRoute();
            }
        } catch (NotEnoughPointsException e) {
            throw new WebApiException(e);
        }
    }

    @Override
    public List<Schedule> searchSteps(Integer routeId) throws WebApiException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("searchSteps(" + routeId + ")");
        }
        try {
            List<Schedule> routesList;
            if (routeId == null) {
                routesList = Collections.EMPTY_LIST;
            } else {
                routesList = Collections.singletonList(routeManager.getSchedule(routeId));
            }
            return routesList;
        } catch (TraceServiceException e) {
            throw new WebApiException(e);
        }
    }

    @Override
    public List<Schedule> searchSteps() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("searchSteps()");
        }
        return routeManager.getSchedules();
    }
}
