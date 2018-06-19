package ru.timestop.route.trace.entities;

import com.google.gson.annotations.SerializedName;
import ru.timestop.utilites.JsonUtil;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 16.06.2018
 */
public class Point {
    @SerializedName(value = "id_route")
    private Integer idRoute;
    @SerializedName(value = "id_point")
    private Integer idPoint;
    @SerializedName(value = "position")
    private Integer position;

    public Point() {
    }

    public Integer getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Integer idRoute) {
        this.idRoute = idRoute;
    }

    public Integer getIdPoint() {
        return idPoint;
    }

    public void setIdPoint(Integer idPoint) {
        this.idPoint = idPoint;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String toString() {
        return JsonUtil.toJson(this);
    }
}
