default Try<T> filter(CheckedPredicate<? super T> predicate) {
        if (isFailure()) {
            return this;
        } else {
            try {
                if (predicate.test(get())) {
                    return this;
                } else {
                    return new Failure<>(new NoSuchElementException("Predicate does not hold for " + get()));
                }
            } catch (Throwable t) {
                return new Failure<>(t);
            }
        }
    }