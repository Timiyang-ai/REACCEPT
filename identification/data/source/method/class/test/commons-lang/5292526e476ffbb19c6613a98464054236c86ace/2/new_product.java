public static <T> T initializeUnchecked(final ConcurrentInitializer<T> initializer) {
        try {
            return initialize(initializer);
        } catch (final ConcurrentException cex) {
            throw new ConcurrentRuntimeException(cex.getCause());
        }
    }