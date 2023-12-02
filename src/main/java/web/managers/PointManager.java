package web.managers;

import web.models.Point;
import web.models.PointData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PointManager {

    private final ArrayList<PointData> points;

    public PointManager() {
        this.points = new ArrayList<>();
    }

    public ArrayList<PointData> getPoints() {
        return points;
    }

    public boolean addPoint(PointData point) {
        return points.add(point);
    }

    public boolean removePoint(PointData point) {
        return points.remove(point);
    }

    public void removeAllPoints() {
        points.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointManager that = (PointManager) o;
        return Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }

    @Override
    public String toString() {
        return "PointManager{" +
                "points=" + points +
                '}';
    }
}
