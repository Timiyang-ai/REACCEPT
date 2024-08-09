public boolean isColinear (Point point)
    {
        int index = points.indexOf(point);

        if ((index > 0) && (index < (points.size() - 1))) {
            Line2D.Double line = new Line2D.Double(
                getPoint(index - 1),
                getPoint(index + 1));
            double        dist = line.ptLineDist(point);

            return dist <= colinearDistance;
        } else {
            return false;
        }
    }