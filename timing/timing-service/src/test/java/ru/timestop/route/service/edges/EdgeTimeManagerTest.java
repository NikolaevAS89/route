package ru.timestop.route.service.edges;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.timestop.route.timing.exception.EdgeNotFoundException;
import ru.timestop.route.timing.exception.NotEnoughPointsException;
import ru.timestop.route.timing.service.ApplicationConfig;
import ru.timestop.route.timing.service.facade.EdgeTimeManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 05.07.2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class EdgeTimeManagerTest {

    @Autowired
    @Qualifier("edgeTimeManager")
    private EdgeTimeManager timeManager;

    @Configuration
    @Import({ApplicationConfig.class})
    static class ContextConfiguration {
    }

    @Test
    public void testEmptyList() {
        try {
            timeManager.calcTraceTime(Collections.EMPTY_LIST);
            Assert.fail("NotEnoughPointsException not throw");
        } catch (NotEnoughPointsException e) {
            //SKIP
        } catch (Exception e) {
            Assert.fail("Wrong exceptions throw");
        }
    }

    @Test
    public void testNotEnothPoint() {
        try {
            List<Integer> routePoints = Collections.singletonList(1);
            timeManager.calcTraceTime(routePoints);
            Assert.fail("NotEnoughPointsException not throw");
        } catch (NotEnoughPointsException e) {
            //SKIP
        } catch (Exception e) {
            Assert.fail("Wrong exceptions throw");
        }
    }

    @Test
    public void testZeroEdge() {
        try {
            List<Integer> routePoints = Arrays.asList(1, 1);
            Integer time = timeManager.calcTraceTime(routePoints);
            Assert.assertEquals(0, (int) time);
        } catch (Exception e) {
            Assert.fail("Exceptions throw");
        }
    }

    @Test
    public void testEdgeTime() {
        try {
            List<Integer> steps1 = Arrays.asList(1, 2);
            Integer time1 = timeManager.calcTraceTime(steps1);
            List<Integer> steps2 = Arrays.asList(2, 1);
            Integer time2 = timeManager.calcTraceTime(steps2);
            Assert.assertEquals("Edge has't constant time", time1, time2);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Wrong exceptions throw");
        }
    }

    @Test
    public void testNonExistingEdgeTime() {
        try {
            List<Integer> steps = Arrays.asList(1, -1);
            Integer time = timeManager.calcTraceTime(steps);
            Assert.fail("Non existing edge has time!");
        } catch (EdgeNotFoundException e) {
            //SKIP
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Wrong exceptions throw");
        }
    }
}
