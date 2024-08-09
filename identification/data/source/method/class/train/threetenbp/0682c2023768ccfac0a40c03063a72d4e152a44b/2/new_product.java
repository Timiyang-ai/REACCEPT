@Override
    public DateTimeObject adjustCalendrical(DateTimeObject calendrical) {
        return calendrical.with(DAY_OF_MONTH, dayOfMonth);
    }