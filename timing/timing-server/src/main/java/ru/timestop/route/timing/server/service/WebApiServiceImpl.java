package ru.timestop.route.timing.server.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.timestop.route.timing.exception.EdgeNotFoundException;
import ru.timestop.route.timing.exception.NotEnoughPointsException;
import ru.timestop.route.timing.service.facade.EdgeTimeManager;
import ru.timestop.utilites.JsonUtil;

import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 12.08.2018
 */
@Component
@Qualifier("webApiService")
public class WebApiServiceImpl implements WebApiService {


    private static final Logger LOG = Logger.getLogger(WebApiServiceImpl.class);

    @Autowired
    @Qualifier("edgeTimeManager")
    private EdgeTimeManager edgeTimeManager;

    @Override
    public Integer calcTraceTime(List<Integer> points) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("calcTraceTime(" + JsonUtil.toJson(points) + ")");
        }
        try {
            return edgeTimeManager.calcTraceTime(points);
        } catch (EdgeNotFoundException | NotEnoughPointsException e) {
            return -1;
        }
    }
}
