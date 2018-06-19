package ru.timestop.route.timing.service.facade;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.timestop.route.timing.exception.EdgeNotFoundException;
import ru.timestop.route.timing.exception.NotEnoughPointsException;
import ru.timestop.route.timing.service.controll.EdgesTimeService;
import ru.timestop.route.timing.service.controll.EdgesTimeServiceImpl;
import ru.timestop.utilites.JsonUtil;

import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 03.07.2018
 */
@Component
@Qualifier("edgeTimeManager")
public class EdgeTimeManagerImpl implements EdgeTimeManager {

    private static final Logger LOG = Logger.getLogger(EdgesTimeServiceImpl.class);

    @Autowired
    @Qualifier("edgesTimeService")
    private EdgesTimeService edgesTimeService;

    @Override
    public Integer calcTraceTime(List<Integer> points) throws NotEnoughPointsException, EdgeNotFoundException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("calcTraceTime(" + JsonUtil.toJson(points) + ")");
        }
        if (points == null || points.size() < 2) {
            throw new NotEnoughPointsException();
        }
        int time = 0;
        Integer last = null;
        for (Integer point : points) {
            if (last != null) {
                time += edgesTimeService.getTime(last, point);
            }
            last = point;
        }
        return time;
    }
}
