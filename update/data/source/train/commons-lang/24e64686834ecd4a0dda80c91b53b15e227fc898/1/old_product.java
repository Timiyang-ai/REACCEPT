public static <T> T defaultIfNull(final T object, final Supplier<T> defaultSupplier) {
        return object != null ? object : defaultSupplier == null ? null : defaultSupplier.get();
    }