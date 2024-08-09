public double sum() {
        double result = 0;
        DoubleIterator iter = values().iterator();
        while (iter.hasNext()) {
            result += iter.nextDouble();
        }
        return result;
    }