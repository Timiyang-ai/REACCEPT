public OptionalBoolean filter(BooleanPredicate predicate) {
        requireNonNull(predicate);
        if (!isPresent())
            return this;
        else
            return predicate.test(this == TRUE) ? this : empty();
    }