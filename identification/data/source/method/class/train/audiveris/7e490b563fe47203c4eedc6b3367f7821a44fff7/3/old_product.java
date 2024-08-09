public void insertPoint (int   index,
                             Point point)
    {
        points.add(index, point);
        fireListeners();
    }