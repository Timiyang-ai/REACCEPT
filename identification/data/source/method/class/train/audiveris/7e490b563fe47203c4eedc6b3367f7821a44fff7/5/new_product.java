public void insertPointAfter (Point point,
                                  Point after)
    {
        int ptIndex = points.indexOf(after);

        if (ptIndex != -1) {
            points.add(ptIndex + 1, point);
        } else {
            throw new IllegalArgumentException("Insertion point not found");
        }
    }