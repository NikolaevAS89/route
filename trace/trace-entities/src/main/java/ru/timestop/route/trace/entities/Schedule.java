package ru.timestop.route.trace.entities;

import com.google.gson.annotations.SerializedName;
import ru.timestop.utilites.JsonUtil;

import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 16.06.2018
 */
public class Schedule {
    @SerializedName(value = "route")
    private Route route;
    @SerializedName(value = "points")
    private List<Point> points;

    public Schedule() {
    }

    public String toString() {
        return JsonUtil.toJson(this);
    }

    public Route getRoute() {
        return route;
    }

    public List<Point> getPoints() {
        return points;
    }

    private void setRoute(Route route) {
        this.route = route;
    }

    private void setPoints(List<Point> points) {
        this.points = points;
    }

    /**
     * {@code ScheduleBuilder} is builder for {@link Schedule}
     */
    public static class ScheduleBuilder {
        private Route route;
        private List<Point> points;

        public ScheduleBuilder setRoute(Route route) {
            this.route = route;
            return this;
        }

        public ScheduleBuilder setPoints(List<Point> points) {
            this.points = points;
            return this;
        }

        public Schedule build(){
            Schedule res = new Schedule();
            res.setRoute(route);
            res.setPoints(points);
            return res;
        }
    }
}
