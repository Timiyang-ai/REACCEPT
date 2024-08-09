public static Scheme parse(String scheme) {
        final Scheme res = SCHEMES.get(Ascii.toLowerCase(requireNonNull(scheme, "scheme")));
        if (res == null) {
            throw new IllegalArgumentException("scheme: " + scheme);
        }

        return res;
    }