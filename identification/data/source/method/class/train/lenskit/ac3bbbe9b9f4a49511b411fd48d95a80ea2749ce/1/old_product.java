public double sum() {
        if (sum == null) {
            double s = 0;
            DoubleIterator iter = values().iterator();
            while (iter.hasNext()) {
                s += iter.nextDouble();
            }
            sum = s;
        }
        return sum;
    }