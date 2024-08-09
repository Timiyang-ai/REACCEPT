public HijrahDate withDayOfMonth(int dayOfMonth) {
        return HijrahDate.hijrahDate(this.era, this.yearOfEra, this.monthOfYear, dayOfMonth);
    }