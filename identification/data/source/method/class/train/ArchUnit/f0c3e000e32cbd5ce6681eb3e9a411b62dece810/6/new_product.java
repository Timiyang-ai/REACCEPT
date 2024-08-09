public static Transformer matching(String packageIdentifier) {
        return new Transformer(packageIdentifier, slicesMatchingDescription(packageIdentifier));
    }