@SuppressWarnings("unchecked")
    default <U> Try<U> flatMap(Function<? super T, ? extends Iterable<? extends U>> mapper) {
        if (isFailure()) {
            return (Failure<U>) this;
        } else {
            return flatMapTry((CheckedFunction<T, Iterable<? extends U>>) mapper::apply);
        }
    }