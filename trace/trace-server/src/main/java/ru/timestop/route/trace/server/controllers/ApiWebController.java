package ru.timestop.route.trace.server.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.timestop.route.trace.entities.Schedule;
import ru.timestop.route.trace.server.service.WebApiService;
import ru.timestop.route.trace.web.TraceRequestMapping;

import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 05.07.2018.
 */
@Controller
public class ApiWebController implements TraceRequestMapping {
    private static final Logger LOG = Logger.getLogger(ApiWebController.class);

    @Autowired
    @Qualifier("webApiService")
    private WebApiService webApiService;

    @RequestMapping(value = {TRACE_API_CREATE}, method = {RequestMethod.POST})
    public ResponseEntity<Integer> addRoute(@RequestBody List<Integer> points) {
        try {
            return new ResponseEntity<>(webApiService.addRoute(points), HttpStatus.OK);
        } catch (Throwable e) {
            LOG.error(e);
            throw e;
        }
    }

    @RequestMapping(value = {TRACE_API_GET}, method = {RequestMethod.GET})
    public ResponseEntity<List<Schedule>> searchSteps(@PathVariable Integer routeId) {
        try {
            return new ResponseEntity<>(webApiService.searchSteps(routeId), HttpStatus.OK);
        } catch (Throwable e) {
            LOG.error(e);
            throw e;
        }
    }

    @RequestMapping(value = {TRACE_API_GETS}, method = {RequestMethod.GET})
    public ResponseEntity<List<Schedule>> searchSteps() {
        try {
            return new ResponseEntity<>(webApiService.searchSteps(), HttpStatus.OK);
        } catch (Throwable e) {
            LOG.error(e);
            throw e;
        }
    }
}
