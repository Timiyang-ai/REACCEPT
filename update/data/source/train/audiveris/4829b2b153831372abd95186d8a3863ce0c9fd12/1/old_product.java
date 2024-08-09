public Point findSegment (Point point)
    {
        Point bestPoint = null;
        double bestDistSq = java.lang.Double.MAX_VALUE;

        if (points.size() < 2) {
            return null;
        }

        Point prevPt = points.get(0);

        for (Point pt : points) {
            // Skip first point
            if (pt == prevPt) {
                continue;
            }

            Line2D.Double line = new Line2D.Double(prevPt, pt);
            double distSq = line.ptSegDistSq(point);

            if (distSq < bestDistSq) {
                bestPoint = prevPt;
                bestDistSq = distSq;
            }

            prevPt = pt;
        }

        if (bestDistSq <= (stickyDistance * stickyDistance)) {
            return bestPoint;
        } else {
            return null;
        }
    }