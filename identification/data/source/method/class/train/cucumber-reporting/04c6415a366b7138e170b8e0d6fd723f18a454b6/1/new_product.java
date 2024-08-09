public static String toValidFileName(String fileName) {
        // adds MAX_VALUE to eliminate minus character which might be returned by hashCode()
        return Long.toString((long) fileName.hashCode() + Integer.MAX_VALUE);
    }