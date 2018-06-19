package ru.timestop.route.service.trace;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.timestop.route.trace.entities.Route;
import ru.timestop.route.trace.entities.Schedule;
import ru.timestop.route.trace.exception.NotEnoughPointsException;
import ru.timestop.route.trace.exception.TraceServiceException;
import ru.timestop.route.trace.service.ApplicationConfig;
import ru.timestop.route.trace.service.facade.RouteManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 16.06.2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class RouteManagerTest {

    @Autowired
    private RouteManager routeManager;

    @Configuration
    @Import({ApplicationConfig.class})
    static class ContextConfiguration {
    }

    @Test
    public void testEmptyTrace() {
        try {
            routeManager.createRoute(new ArrayList<>());
            Assert.fail("NotEnoughPointsException not throwed");
        } catch (NotEnoughPointsException e) {
            //SKIP
        } catch (Throwable e) {
            Assert.fail("Wrong exceptions was throw");
        }
    }

    @Test
    public void testSinglePointTrace() {
        try {
            List<Integer> pIds = Arrays.asList(new Integer[]{-1});
            routeManager.createRoute(pIds);
            Assert.fail("NotEnoughPointsException not throwed");
        } catch (NotEnoughPointsException e) {
            //SKIP
        } catch (Throwable e) {
            Assert.fail("Wrong exceptions was throw");
        }
    }

    @Test
    public void testFindById() {
        try {
            List<Integer> pIds = Arrays.asList(new Integer[]{4, 2, 9, 4, 5});
            Route route = routeManager.createRoute(123, pIds);
            Schedule schedule = routeManager.getSchedule(route.getIdRoute());
            Assert.assertEquals(5, schedule.getPoints().size());
            Assert.assertEquals(Boolean.TRUE, schedule.getRoute().getIsReady());
            Assert.assertEquals(123, schedule.getRoute().getTime().intValue());
        } catch (TraceServiceException e) {
            Assert.fail("Exception " + e.getClass() + " throws");
        }
    }
}
