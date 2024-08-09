public static Charset defaultCharset() {
        // Android-changed: Use UTF_8 unconditionally.
        synchronized (Charset.class) {
            if (defaultCharset == null) {
                defaultCharset = java.nio.charset.StandardCharsets.UTF_8;
            }

            return defaultCharset;
        }
    }