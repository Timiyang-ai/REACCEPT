default IsNotNull<T, R> isNotNull() {
        return new IsNotNullImpl<>(this);
    }