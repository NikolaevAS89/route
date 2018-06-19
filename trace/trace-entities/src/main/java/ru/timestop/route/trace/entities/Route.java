package ru.timestop.route.trace.entities;

import com.google.gson.annotations.SerializedName;
import ru.timestop.utilites.JsonUtil;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 23.07.2018
 */
public class Route {
    @SerializedName(value = "id_route")
    private Integer idRoute;
    @SerializedName(value = "is_ready")
    private Boolean isReady;
    @SerializedName(value = "time")
    private Integer time;

    public Route() {
    }

    public Integer getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Integer routeId) {
        this.idRoute = routeId;
    }

    public Boolean getIsReady() {
        return isReady;
    }

    public void setIsReady(Boolean ready) {
        isReady = ready;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String toString() {
        return JsonUtil.toJson(this);
    }
}