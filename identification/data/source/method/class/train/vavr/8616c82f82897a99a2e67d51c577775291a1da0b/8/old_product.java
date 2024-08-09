@SuppressWarnings("unchecked")
    default <U> Try<U> flatMap(Function<? super T, ? extends Try<? extends U>> mapper) {
        if (isFailure()) {
            return (Failure<U>) this;
        } else {
            return flatMapTry((CheckedFunction<T, Try<U>>) mapper);
        }
    }