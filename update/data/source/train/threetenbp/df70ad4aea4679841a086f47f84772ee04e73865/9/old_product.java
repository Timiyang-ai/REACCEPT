public HijrahDate withYear(HijrahEra era, int yearOfEra) {
        return HijrahDate.hijrahDate(era, yearOfEra, this.monthOfYear, this.dayOfMonth);
    }