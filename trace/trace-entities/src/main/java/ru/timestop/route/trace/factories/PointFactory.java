package ru.timestop.route.trace.factories;

import ru.timestop.route.trace.entities.Point;

public class PointFactory {
    private Integer idRoute;
    private Integer idPoint;
    private int position;

    public PointFactory(Integer idRoute) {
        this.idRoute = idRoute;
        this.position = new Integer(1);
    }

    public PointFactory setPointId(Integer idPoint) {
        this.idPoint = idPoint;
        return this;
    }

    public Point build() {
        Point res = new Point();
        res.setIdPoint(idPoint);
        res.setIdRoute(idRoute);
        res.setPosition(position++);
        return res;
    }
}
