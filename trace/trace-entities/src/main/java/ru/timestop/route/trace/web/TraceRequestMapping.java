package ru.timestop.route.trace.web;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 02.08.2018.
 */
public interface TraceRequestMapping {
    // request path for create new route by list of points ids
    String TRACE_API_CREATE = "/v1/public/api/trace/add";
    // request path for search all reistred shedules
    String TRACE_API_GETS = "/v1/public/api/trace/gets";
    // request path for search shedule by id
    String TRACE_API_GET = "/v1/private/api/trace/get/{routeId}";
}
