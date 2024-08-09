@Override
    public ChronoLocalDate<HijrahChronology> date(int year, int month, int day) {
        return getChronology().date(this, year, month, day);
    }