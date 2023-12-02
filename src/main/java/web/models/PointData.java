package web.models;

import java.util.Objects;

import static java.lang.Math.pow;

public class PointData {

    private Point point;
    private double radius;
    private boolean status;

    public PointData(Point point, double radius) {
        this.point = point;
        this.radius = radius;
        this.status = isInArea(point.getX(), point.getY(), radius);
    }

    public Point getPoint() {
        return point;
    }

    public double getRadius() {
        return radius;
    }

    public boolean getStatus() {
        return status;
    }

    private boolean isInArea(double x, double y, double r) {
        if (x <= 0 && y <= 0)
            return (x <= r / 2) && (y <= r);

        if (x >= 0 && y <= 0)
            return (x * x + y * y) <= pow(r / 2, 2);

        if (x >= 0 && y >= 0)
            return (x <= r) && (y <= r) && (y + x <= r); // y = -x + r

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointData pointData = (PointData) o;
        return Double.compare(pointData.radius, radius) == 0 && status == pointData.status && point.equals(pointData.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, radius, status);
    }

    @Override
    public String toString() {
        return "PointData{" +
                "point=" + point +
                ", radius=" + radius +
                ", status=" + status +
                '}';
    }
}
