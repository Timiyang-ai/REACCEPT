    @Override
    public boolean filter(Bundle row) {
        boolean ret = false;
        do {
            if (test.filter(row)) {
                ret = onTrue.filter(row);
            } else {
                ret = onFalse.filter(row);
            }
        }
        while (ret && --loop != 0);
        return ret;
    }