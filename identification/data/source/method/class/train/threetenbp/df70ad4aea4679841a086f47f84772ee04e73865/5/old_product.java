public HijrahDate withDayOfYear(int dayOfYear) {
        HijrahChronology.dayOfYearRule().checkValue(dayOfYear);
        return HijrahDate.hijrahDate(this.era, this.yearOfEra, 1, 1).plusDays(dayOfYear).plusDays(-1);
    }