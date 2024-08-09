public HijrahDate withDayOfYear(int dayOfYear) {
        HijrahChronology.dayOfYearRule().checkValue(dayOfYear);
        return HijrahDate.of(this.era, this.yearOfEra, 1, 1).plusDays(dayOfYear).plusDays(-1);
    }