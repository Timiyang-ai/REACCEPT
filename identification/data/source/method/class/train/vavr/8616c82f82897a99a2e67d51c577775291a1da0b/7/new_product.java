public final <U> Try<U> flatMap(Function<? super T, ? extends Try<? extends U>> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        return flatMapTry((CheckedFunction1<T, Try<? extends U>>) mapper::apply);
    }