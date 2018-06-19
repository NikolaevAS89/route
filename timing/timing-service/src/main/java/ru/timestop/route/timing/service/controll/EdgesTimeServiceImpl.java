package ru.timestop.route.timing.service.controll;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.timestop.route.timing.exception.EdgeNotFoundException;
import ru.timestop.route.timing.exception.TimingServiceException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 02.07.2018
 */
@Component
@Qualifier("edgesTimeService")
public class EdgesTimeServiceImpl implements EdgesTimeService {

    private static final Logger LOG = Logger.getLogger(EdgesTimeServiceImpl.class);

    @Autowired
    @Qualifier("edgeTimeJdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Integer getTime(Integer pointOne, Integer pointTwo) throws EdgeNotFoundException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("getTime(" + pointOne + ", " + pointTwo + ")");
        }
        if (pointOne.equals(pointTwo)) {
            return 0;
        }
        // first point have greatest id (it is db feature)
        if (pointOne - pointTwo < 0) {
            int point_buf = pointOne;
            pointOne = pointTwo;
            pointTwo = point_buf;
        }
        Map<String, Integer> param = new HashMap<>(2, 2.0f);
        param.put("id_point_one", pointOne);
        param.put("id_point_second", pointTwo);

        String sql = "select e.time " +
                " from edges e " +
                " where e.id_point_one = :id_point_one " +
                "   and e.id_point_second = :id_point_second";

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    param,
                    Integer.class);
        } catch (EmptyResultDataAccessException e) {
            LOG.error(e);
            throw new EdgeNotFoundException(pointOne, pointTwo);
        }
    }
}
