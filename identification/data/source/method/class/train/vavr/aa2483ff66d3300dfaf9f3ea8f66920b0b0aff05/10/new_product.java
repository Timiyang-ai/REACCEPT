public final Try<T> filter(Predicate<? super T> predicate, Supplier<? extends Throwable> throwableSupplier) {
        Objects.requireNonNull(predicate, "predicate is null");
        Objects.requireNonNull(throwableSupplier, "throwableSupplier is null");
        return filterTry(predicate::test, throwableSupplier);
    }