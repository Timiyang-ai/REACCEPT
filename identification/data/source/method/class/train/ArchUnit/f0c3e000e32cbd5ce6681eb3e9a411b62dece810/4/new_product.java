@PublicAPI(usage = ACCESS)
    public static Transformer matching(String packageIdentifier) {
        PackageMatchingSliceIdentifier sliceIdentifier = new PackageMatchingSliceIdentifier(packageIdentifier);
        String description = "slices matching " + sliceIdentifier.getDescription();
        return new Transformer(sliceIdentifier, description);
    }