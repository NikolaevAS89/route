package ru.timestop.route.timing.entities;

import com.google.gson.annotations.SerializedName;
import ru.timestop.utilites.JsonUtil;


/**
 * Object contain time for path from point one to point two.
 *
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 16.06.2018
 */
public class Edge {
    @SerializedName(value = "id")
    private Integer id;
    @SerializedName(value = "id_point_one")
    private Integer idPointOne;
    @SerializedName(value = "id_point_second")
    private Integer idPointSecond;
    @SerializedName(value = "time")
    private Integer time;

    public Edge() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPointOne() {
        return idPointOne;
    }

    public void setIdPointOne(Integer idPointOne) {
        this.idPointOne = idPointOne;
    }

    public Integer getIdPointSecond() {
        return idPointSecond;
    }

    public void setIdPointSecond(Integer idPointSecond) {
        this.idPointSecond = idPointSecond;
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
