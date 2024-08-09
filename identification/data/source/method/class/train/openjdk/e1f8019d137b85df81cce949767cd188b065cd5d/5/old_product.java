@Override
    HijrahDate plusYears(long years) {
        if (years == 0) {
            return this;
        }
        int newYear = Math.addExact(this.yearOfEra, (int)years);
        return HijrahDate.of(chrono, this.era, newYear, this.monthOfYear, this.dayOfMonth);
    }