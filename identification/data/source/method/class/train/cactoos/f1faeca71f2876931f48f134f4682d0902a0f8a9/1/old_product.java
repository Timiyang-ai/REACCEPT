@Override
    public final boolean equals(final Object obj) {
        return new UncheckedScalar<>(this.origin).value().equals(obj);
    }