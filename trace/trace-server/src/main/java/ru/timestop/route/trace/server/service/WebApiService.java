package ru.timestop.route.trace.server.service;

import ru.timestop.route.trace.entities.Schedule;
import ru.timestop.exceptions.WebApiException;

import java.util.List;

public interface WebApiService {
    Integer addRoute(List<Integer> points) throws WebApiException;

    List<Schedule> searchSteps(Integer routeId) throws WebApiException;

    List<Schedule> searchSteps();
}
