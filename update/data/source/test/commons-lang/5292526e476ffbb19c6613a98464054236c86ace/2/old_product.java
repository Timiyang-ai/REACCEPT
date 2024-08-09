public static <T> T initializeUnchecked(final ConcurrentInitializer<T> initializer) {
        try {
            return initialize(initializer);
        } catch (ConcurrentException cex) {
            throw new ConcurrentRuntimeException(cex.getCause());
        }
    }