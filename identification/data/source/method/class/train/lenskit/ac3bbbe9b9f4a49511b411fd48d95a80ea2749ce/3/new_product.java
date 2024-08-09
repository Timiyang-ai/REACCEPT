public double norm() {
        double ssq = 0;
        DoubleIterator iter = values().iterator();
        while (iter.hasNext()) {
            double v = iter.nextDouble();
            ssq += v * v;
        }
        double result = Math.sqrt(ssq);
        return result;
    }