public Point findPoint (Point point)
    {
        Rectangle window = new Rectangle(point);
        window.grow(stickyDistance, stickyDistance);

        for (Point pt : points) {
            if (window.contains(pt)) {
                return pt;
            }
        }

        return null;
    }