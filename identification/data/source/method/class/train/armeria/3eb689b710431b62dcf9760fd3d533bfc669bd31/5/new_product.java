public static Optional<Scheme> tryParse(@Nullable String scheme) {
        if (scheme == null) {
            return Optional.empty();
        }
        final String lowercaseScheme = Ascii.toLowerCase(scheme);
        final Scheme parsedScheme = SCHEMES.get(lowercaseScheme);
        if (parsedScheme != null) {
            return Optional.of(parsedScheme);
        }
        return Optional.ofNullable(SCHEMES.get(SerializationFormat.NONE.uriText() + '+' + lowercaseScheme));
    }