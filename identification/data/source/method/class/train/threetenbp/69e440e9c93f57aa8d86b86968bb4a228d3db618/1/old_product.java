public Period minus(Period other) {
        return create(
                Jdk8Methods.safeSubtract(years, other.years),
                Jdk8Methods.safeSubtract(months, other.months),
                Jdk8Methods.safeSubtract(days, other.days),
                Jdk8Methods.safeSubtract(nanos, other.nanos));
    }