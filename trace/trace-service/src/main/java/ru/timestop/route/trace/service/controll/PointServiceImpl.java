package ru.timestop.route.trace.service.controll;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.timestop.route.trace.entities.Point;
import ru.timestop.route.trace.entities.Route;
import ru.timestop.route.trace.factories.PointFactory;
import ru.timestop.utilites.JsonUtil;

import java.util.Collections;
import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 17.06.2018
 */
@Repository
@Qualifier("TraceService")
public class PointServiceImpl implements PointService {
    private static final Logger LOG = LoggerFactory.getLogger(PointServiceImpl.class);

    @Autowired
    @Qualifier("traceJdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("traceDbcpDataSource")
    private BasicDataSource dataSource;

    // mapper for parse result sets of request
    private static final BeanPropertyRowMapper<Point> MAPPER = new BeanPropertyRowMapper<>(Point.class);

    @Override
    public void registerPoints(Route route, List<Integer> points) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("registerPoints(" + JsonUtil.toJson(route) + ", " + JsonUtil.toJson(points) + ")");
        }

        PointFactory factory = new PointFactory(route.getIdRoute());

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("points");

        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(points
                .stream()
                .map(point -> factory.setPointId(point).build())
                .toArray());

        jdbcInsert.executeBatch(batch);
    }

    @Override
    public List<Point> getRoutePoints() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("getRoutePoints()");
        }

        String sql = "select * from points order by id_route, position";

        return jdbcTemplate.query(
                sql,
                MAPPER);
    }

    @Override
    public List<Point> getRoutePoints(Route route) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("getRoutePoints(" + JsonUtil.toJson(route) + ")");
        }

        String sql = "select * from points where id_route=:id order by id_route, position";

        return jdbcTemplate.query(
                sql,
                Collections.singletonMap("id", route.getIdRoute()),
                MAPPER);
    }
}
