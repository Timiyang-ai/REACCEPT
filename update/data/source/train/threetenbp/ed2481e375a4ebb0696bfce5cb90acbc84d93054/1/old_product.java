@Override
    public ChronoLocalDate<HijrahChrono> date(int year, int month, int day) {
        return getChrono().date(this, year, month, day);
    }