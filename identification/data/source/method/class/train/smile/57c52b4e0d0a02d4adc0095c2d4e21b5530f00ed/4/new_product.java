@Override
    public int predict(T x) {
        List<Neighbor<T,T>> neighbors = new ArrayList<>();
        nns.range(x, radius, neighbors);
        
        if (neighbors.size() < minPts) {
            return OUTLIER;
        }
        
        int[] label = new int[k + 1];
        for (Neighbor<T,T> neighbor : neighbors) {
            int yi = y[neighbor.index];
            if (yi == OUTLIER) yi = k;
            label[yi]++;
        }
        
        int c = Math.whichMax(label);
        if (c == k) c = OUTLIER;
        return c;
    }