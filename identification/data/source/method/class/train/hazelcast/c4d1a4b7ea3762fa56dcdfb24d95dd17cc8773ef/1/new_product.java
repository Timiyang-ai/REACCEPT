public static void checkNotNative(InMemoryFormat inMemoryFormat) {
        if (NATIVE == inMemoryFormat) {
            throw new IllegalArgumentException("NATIVE storage format is supported in Hazelcast Enterprise only. "
                    + "Make sure you have Hazelcast Enterprise JARs on your classpath!");
        }
    }