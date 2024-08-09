public Period minus(Period amountToSubtract) {
        return create(
                Jdk8Methods.safeAdd(years, amountToSubtract.years),
                Jdk8Methods.safeAdd(months, amountToSubtract.months),
                Jdk8Methods.safeAdd(days, amountToSubtract.days));
    }