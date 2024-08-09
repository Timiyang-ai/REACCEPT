@Override
    HijrahDate plusYears(long years) {
        if (years == 0) {
            return this;
        }
        int newYear = Math.addExact(this.prolepticYear, (int)years);
        return resolvePreviousValid(newYear, monthOfYear, dayOfMonth);
    }