default Try<T> filter(Predicate<? super T> predicate, Function<? super T, ? extends Throwable> errorProvider) {
        Objects.requireNonNull(predicate, "predicate is null");
        Objects.requireNonNull(errorProvider, "errorProvider is null");
        return filterTry(predicate::test, errorProvider::apply);
    }