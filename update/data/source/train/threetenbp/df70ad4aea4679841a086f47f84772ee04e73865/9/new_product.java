public HijrahDate withYear(HijrahEra era, int yearOfEra) {
        return HijrahDate.of(era, yearOfEra, this.monthOfYear, this.dayOfMonth);
    }