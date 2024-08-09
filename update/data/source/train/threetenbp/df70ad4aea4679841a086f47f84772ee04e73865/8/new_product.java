public HijrahDate minusMonths(int months) {
        if (months == 0) {
            return this;
        }
        int years = months / 12;
        int newMonth = this.monthOfYear - 1;
        newMonth = newMonth - (months % 12);
        if (newMonth >= 12) {
            newMonth = newMonth % 12;
            years = MathUtils.safeDecrement(years);
        } else if (newMonth < 0) {
            newMonth += 12;
            years = MathUtils.safeIncrement(years);
        }
        int newYear = MathUtils.safeAdd(this.yearOfEra, years);
        return HijrahDate.of(this.era, newYear, (newMonth + 1), this.dayOfMonth);
    }