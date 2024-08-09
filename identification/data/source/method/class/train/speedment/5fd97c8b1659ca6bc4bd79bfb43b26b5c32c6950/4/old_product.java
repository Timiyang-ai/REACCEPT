default IsNull<T, R, NON_NULLABLE> isNull() {
        return new IsNullImpl<>(this);
    }