public double mean() {
        final int sz = size();
        double result = sz > 0 ? sum() / sz : 0;
        return result;
    }