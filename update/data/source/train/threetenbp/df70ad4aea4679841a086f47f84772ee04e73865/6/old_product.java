public HijrahDate plusMonths(int months) {
        if (months == 0) {
            return this;
        }
        int newMonth = this.monthOfYear - 1;
        newMonth = newMonth + months;
        int years = newMonth / 12;
        newMonth = newMonth % 12;
        if (newMonth < 0) {
            newMonth += 12;
            years = MathUtils.safeDecrement(years);
        }
        int newYear = MathUtils.safeAdd(this.yearOfEra, years);
        return HijrahDate.hijrahDate(this.era, newYear, newMonth + 1, this.dayOfMonth);
    }