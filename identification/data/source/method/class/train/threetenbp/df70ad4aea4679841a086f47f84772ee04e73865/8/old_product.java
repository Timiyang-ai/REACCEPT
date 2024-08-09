public HijrahDate withMonthOfYear(int monthOfYear) {
        return HijrahDate.hijrahDate(this.era, this.yearOfEra, monthOfYear, this.dayOfMonth);
    }