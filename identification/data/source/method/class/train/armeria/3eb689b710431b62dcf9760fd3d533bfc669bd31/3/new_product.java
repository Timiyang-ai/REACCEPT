public static Scheme parse(String scheme) {
        final Optional<Scheme> parsedScheme = tryParse(requireNonNull(scheme, "scheme"));
        return parsedScheme.orElseThrow(() -> new IllegalArgumentException("scheme: " + scheme));
    }