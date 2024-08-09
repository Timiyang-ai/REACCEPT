@SuppressWarnings("unchecked")
    @Override
    default <U> Try<U> flatMap(Function<? super T, ? extends java.lang.Iterable<? extends U>> mapper) {
        if (isFailure()) {
            return (Failure<U>) this;
        } else {
            return flatMapTry((CheckedFunction<T, java.lang.Iterable<? extends U>>) mapper::apply);
        }
    }