public double norm() {
        if (norm == null) {
            double ssq = 0;
            DoubleIterator iter = values().iterator();
            while (iter.hasNext()) {
                double v = iter.nextDouble();
                ssq += v * v;
            }
            norm = Math.sqrt(ssq);
        }
        return norm;
    }