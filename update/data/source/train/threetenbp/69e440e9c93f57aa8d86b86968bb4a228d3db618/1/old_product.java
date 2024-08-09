public Period plus(Period other) {
        return create(
                Jdk8Methods.safeAdd(years, other.years),
                Jdk8Methods.safeAdd(months, other.months),
                Jdk8Methods.safeAdd(days, other.days),
                Jdk8Methods.safeAdd(nanos, other.nanos));
    }