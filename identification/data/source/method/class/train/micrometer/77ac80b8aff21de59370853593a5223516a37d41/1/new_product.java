@Override
    public String name(String name, Meter.Type type, @Nullable String baseUnit) {
        String sanitizedName = delegate.name(name, type, baseUnit).replaceAll("[^a-zA-Z0-9\\-_\\./,]", "_");

        // add name prefix if prefix exists
        if (namePrefix != null) {
            return namePrefix + "." + sanitizedName;
        }
        return sanitizedName;
    }