package ru.timestop.route.trace.service.controll;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.timestop.route.trace.entities.Route;
import ru.timestop.utilites.JsonUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 27.07.2018
 */
@Repository
@Qualifier("RouteService")
public class RouteServiceImpl implements RouteService {
    private static final Logger LOG = LoggerFactory.getLogger(RouteServiceImpl.class);

    @Autowired
    @Qualifier("traceDbcpDataSource")
    private BasicDataSource dataSource;

    @Autowired
    @Qualifier("traceJdbcTemplate")
    private NamedParameterJdbcTemplate template;

    private static final BeanPropertyRowMapper<Route> MAPPER = new BeanPropertyRowMapper<>(Route.class);

    @Override
    public Route getRoute(Integer routeId) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("getRouteTiming(" + JsonUtil.toJson(routeId) + ")");
        }
        String sql = "select * from routes where id_route=:id_route";

        Map<String, Integer> params = Collections.singletonMap("id_route", routeId);

        return template.queryForObject(sql, params, MAPPER);
    }

    @Override
    public List<Route> getRoutes() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("getRoutes()");
        }
        String sql = "select * from routes";

        return template.query(sql, MAPPER);
    }

    @Override
    public Route createRoute(Boolean isReady, Integer time) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("createRoute(" + isReady + "" + time + ")");
        }
        Route route = new Route();
        route.setIsReady(isReady);
        route.setTime(time);

        SqlParameterSource source = new BeanPropertySqlParameterSource(route);

        KeyHolder keyHolder = new SimpleJdbcInsert(dataSource)
                .withTableName("routes")
                .usingGeneratedKeyColumns("id_route")
                .executeAndReturnKeyHolder(source);

        Integer key = keyHolder.getKey().intValue();
        route.setIdRoute(key);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Route " + key + " is created");
        }
        return route;
    }
}
