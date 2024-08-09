@SuppressLint("DefaultLocale")
    public static String byte2FitMemorySize(final long byteSize) {
        if (byteSize < 0) {
            return "shouldn't be less than zero!";
        } else if (byteSize < MemoryConstants.KB) {
            return String.format("%.3fB", (double) byteSize);
        } else if (byteSize < MemoryConstants.MB) {
            return String.format("%.3fKB", (double) byteSize / MemoryConstants.KB);
        } else if (byteSize < MemoryConstants.GB) {
            return String.format("%.3fMB", (double) byteSize / MemoryConstants.MB);
        } else {
            return String.format("%.3fGB", (double) byteSize / MemoryConstants.GB);
        }
    }