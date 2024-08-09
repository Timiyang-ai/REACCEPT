public HijrahDate withMonthOfYear(int monthOfYear) {
        return HijrahDate.of(this.era, this.yearOfEra, monthOfYear, this.dayOfMonth);
    }