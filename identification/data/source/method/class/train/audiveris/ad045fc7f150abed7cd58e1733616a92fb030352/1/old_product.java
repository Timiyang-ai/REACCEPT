public Point findPoint (Point point)
    {
        Rectangle window = new Rectangle(
            point.x - stickyDistance,
            point.y - stickyDistance,
            2 * stickyDistance,
            2 * stickyDistance);

        for (Point pt : points) {
            if (window.contains(pt)) {
                return pt;
            }
        }

        return null;
    }