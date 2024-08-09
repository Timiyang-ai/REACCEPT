public double mean() {
        if (mean == null) {
            final int sz = size();
            mean = sz > 0 ? sum() / sz : 0;
        }
        return mean;
    }