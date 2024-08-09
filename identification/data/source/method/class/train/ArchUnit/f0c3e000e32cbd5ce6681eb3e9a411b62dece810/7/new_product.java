public static Transformer matching(String packageIdentifier) {
        return new Transformer(packageIdentifier, String.format("slices matching '%s'", packageIdentifier));
    }