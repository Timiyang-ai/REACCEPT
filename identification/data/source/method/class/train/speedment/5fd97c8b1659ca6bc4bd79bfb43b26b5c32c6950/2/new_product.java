default IsNotNull<T, R, NON_NULLABLE> isNotNull() {
        return new IsNotNullImpl<>(this);
    }