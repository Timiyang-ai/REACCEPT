public Period plus(Period amountToAdd) {
        return create(
                Jdk8Methods.safeAdd(years, amountToAdd.years),
                Jdk8Methods.safeAdd(months, amountToAdd.months),
                Jdk8Methods.safeAdd(days, amountToAdd.days));
    }