package ru.timestop.route.timing.service.controll;

import ru.timestop.route.timing.exception.EdgeNotFoundException;

/**
 * Service for manipilate get, create and delete edges in database.
 * Points in all methods will has be ordered by identifier from highest to lowest.
 *
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 02.07.2018
 */
public interface EdgesTimeService {
    /**
     * Calculete edge time or throw exception if edge does not exist.
     *
     * @param pointOne first point of edge
     * @param pointTwo second point of edge
     * @return edge time
     */
    Integer getTime(Integer pointOne, Integer pointTwo) throws EdgeNotFoundException;
}