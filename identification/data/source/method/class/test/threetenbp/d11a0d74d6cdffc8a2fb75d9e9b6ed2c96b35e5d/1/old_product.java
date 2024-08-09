@Override
    public DateTime doAdjustment(DateTime calendrical) {
        return calendrical.with(DAY_OF_MONTH, dayOfMonth);
    }