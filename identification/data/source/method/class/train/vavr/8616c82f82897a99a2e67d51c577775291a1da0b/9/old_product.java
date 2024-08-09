@SuppressWarnings("unchecked")
    default <U> Try<U> flatMap(CheckedFunction<? super T, ? extends Try<? extends U>> mapper) {
        if (isFailure()) {
            return (Failure<U>) this;
        } else {
            try {
                return (Try<U>) mapper.apply(get());
            } catch (Throwable t) {
                return new Failure<>(t);
            }
        }
    }