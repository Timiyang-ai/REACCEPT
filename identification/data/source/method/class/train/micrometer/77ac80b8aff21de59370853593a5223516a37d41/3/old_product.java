@Override
    public String name(String name, Meter.Type type, @Nullable String baseUnit) {
        // TODO sanitize names of unacceptable characters
        return delegate.name(name, type, baseUnit);
    }