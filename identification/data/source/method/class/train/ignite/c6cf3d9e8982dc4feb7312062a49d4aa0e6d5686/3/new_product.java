@Override public Double get() {
        double res = generator().nextDouble() * (to - from) + from;
        if (res > to)
            res = to;

        return res;
    }