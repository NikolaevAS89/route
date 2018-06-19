package ru.timestop.route.timing.server.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.timestop.exceptions.WebApiException;
import ru.timestop.route.timing.server.service.WebApiService;
import ru.timestop.route.timing.web.TimingRequestMapping;

import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 05.07.2018.
 */
@Controller
public class ApiWebController implements TimingRequestMapping {
    private static final Logger LOG = Logger.getLogger(ApiWebController.class);

    @Autowired
    @Qualifier("webApiService")
    private WebApiService webApiService;

    @RequestMapping(value = {TIMING_API_CALCULATE}, method = {RequestMethod.POST})
    public ResponseEntity<Integer> calcTiming(@RequestBody List<Integer> points) {
        try {
            Integer time = webApiService.calcTraceTime(points);
            return new ResponseEntity<>(time, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e);
            throw new WebApiException(e);
        }
    }
}
