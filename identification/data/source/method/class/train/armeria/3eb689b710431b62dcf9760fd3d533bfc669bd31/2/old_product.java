public static Scheme parse(String scheme) {
        final Scheme res = SCHEMES.get(requireNonNull(scheme, "scheme").toLowerCase(Locale.US));
        if (res == null) {
            throw new IllegalArgumentException("scheme: " + scheme);
        }

        return res;
    }