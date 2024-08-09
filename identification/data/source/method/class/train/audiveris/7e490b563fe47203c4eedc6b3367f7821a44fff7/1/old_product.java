public void removePoint (Point point)
    {
        points.remove(point);
        fireListeners();
    }