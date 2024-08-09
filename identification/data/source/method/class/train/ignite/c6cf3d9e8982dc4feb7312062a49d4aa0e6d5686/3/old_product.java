@Override public Double get() {
        double result = generator().nextDouble() * (to - from) + from;
        if (result > to)
            result = to;

        return result;
    }