default IsNull<T, R> isNull() {
        return new IsNullImpl<>(this);
    }