@Override
    public String name(String name, Meter.Type type, @Nullable String baseUnit) {
        String delegatedName = this.delegate.name(name, type, baseUnit);
        String sanitizedName = PATTERN_NAME_TO_SANITIZE.matcher(delegatedName).replaceAll("_");

        // add name prefix if prefix exists
        if (namePrefix != null) {
            return namePrefix + "." + sanitizedName;
        }
        return sanitizedName;
    }