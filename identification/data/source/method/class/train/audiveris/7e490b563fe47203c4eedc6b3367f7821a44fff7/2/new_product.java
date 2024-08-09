public void movePoint (Point point,
                           Point location)
    {
        if (point == null) {
            throw new IllegalArgumentException("Cannot move a null point");
        }

        point.setLocation(location);
    }