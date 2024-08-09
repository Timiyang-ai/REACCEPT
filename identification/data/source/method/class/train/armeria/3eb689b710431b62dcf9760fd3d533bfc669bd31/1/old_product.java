public static Optional<Scheme> tryParse(String scheme) {
        if (scheme == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(SCHEMES.get(Ascii.toLowerCase(scheme)));
    }