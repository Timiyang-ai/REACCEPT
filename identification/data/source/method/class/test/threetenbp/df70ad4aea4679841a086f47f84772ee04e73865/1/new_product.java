public HijrahDate withDayOfMonth(int dayOfMonth) {
        return HijrahDate.of(this.era, this.yearOfEra, this.monthOfYear, dayOfMonth);
    }