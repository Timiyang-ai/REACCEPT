@Override
    public ChronoDate plus(long periodAmount, PeriodUnit unit) {
        if (unit instanceof LocalDateTimeUnit) {
            LocalDateTimeUnit f = (LocalDateTimeUnit) unit;
            switch (f) {
                case DAYS: return plusDays(periodAmount);
                case WEEKS: return plusDays(DateTimes.safeMultiply(periodAmount, 7));
                case MONTHS: return plusMonths(periodAmount);
                case QUARTER_YEARS: return plusYears(periodAmount / 256).plusMonths((periodAmount % 256) * 3);  // no overflow (256 is multiple of 4)
                case HALF_YEARS: return plusYears(periodAmount / 256).plusMonths((periodAmount % 256) * 6);  // no overflow (256 is multiple of 2)
                case YEARS: return plusYears(periodAmount);
                case DECADES: return plusYears(DateTimes.safeMultiply(periodAmount, 10));
                case CENTURIES: return plusYears(DateTimes.safeMultiply(periodAmount, 100));
                case MILLENNIA: return plusYears(DateTimes.safeMultiply(periodAmount, 1000));
//                case ERAS: throw new CalendricalException("Unable to add era, standard calendar system only has one era");
//                case FOREVER: return (period == 0 ? this : (period > 0 ? LocalDate.MAX_DATE : LocalDate.MIN_DATE));
            }
            throw new CalendricalException(unit.getName() + " not valid for CopticDate");
        }
        return unit.doAdd(this, periodAmount);
    }