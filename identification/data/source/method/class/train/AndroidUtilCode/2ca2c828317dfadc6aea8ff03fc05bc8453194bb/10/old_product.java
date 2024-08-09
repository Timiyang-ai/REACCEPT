public static String getFileSize(final String filePath) {
        long len = getFileLength(filePath);
        return len == -1 ? "" : byte2FitMemorySize(len);
    }