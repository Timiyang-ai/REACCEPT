@Override
    public DateTime doWithAdjustment(DateTime calendrical) {
        return calendrical.with(DAY_OF_MONTH, dayOfMonth);
    }