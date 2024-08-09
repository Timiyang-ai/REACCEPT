public OptionalBoolean filter(BooleanPredicate predicate) {
        if (this == EMPTY || predicate.test(getAsBoolean())) {
            return this;
        } else {
            return EMPTY;
        }
    }