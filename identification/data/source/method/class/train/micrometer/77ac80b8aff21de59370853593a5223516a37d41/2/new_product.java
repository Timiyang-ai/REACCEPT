@Override
    public String name(String name, Meter.Type type, @Nullable String baseUnit) {
        // FIXME sanitize names of unacceptable characters

        // add name prefix if prefix exists
        if (namePrefix != null) {
            return namePrefix + "." + delegate.name(name, type, baseUnit);
        }
        return delegate.name(name, type, baseUnit);
    }