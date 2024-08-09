    @Override
    protected <T> Either<?, T> empty() {
        return Either.<T, T> left(null);
    }