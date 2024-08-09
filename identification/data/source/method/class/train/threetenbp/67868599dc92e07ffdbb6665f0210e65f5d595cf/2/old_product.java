@Override
    public YearMonth plus(long period, PeriodUnit unit) {
        if (unit instanceof LocalDateUnit) {
            switch ((LocalDateUnit) unit) {
                case MONTHS: return plusMonths(period);
                case QUARTER_YEARS: return plusYears(period / 256).plusMonths((period % 256) * 3);  // no overflow (256 is multiple of 4)
                case HALF_YEARS: return plusYears(period / 256).plusMonths((period % 256) * 6);  // no overflow (256 is multiple of 2)
                case YEARS: return plusYears(period);
                case DECADES: return plusYears(DateTimes.safeMultiply(period, 10));
                case CENTURIES: return plusYears(DateTimes.safeMultiply(period, 100));
                case MILLENIA: return plusYears(DateTimes.safeMultiply(period, 1000));
            }
            throw new CalendricalException(unit.getName() + " not valid for YearMonth");
        } else if (unit instanceof LocalTimeUnit) {
            throw new CalendricalException(unit.getName() + " not valid for YearMonth");
        }
        return unit.add(this, period);
    }