@SuppressLint("DefaultLocale")
    public static String byte2FitMemorySize(long byteNum) {
        if (byteNum < 0) {
            return "shouldn't be less than zero!";
        } else if (byteNum < MemoryConstants.KB) {
            return String.format("%.3fB", (double) byteNum + 0.0005);
        } else if (byteNum < MemoryConstants.MB) {
            return String.format("%.3fKB", (double) byteNum / MemoryConstants.KB + 0.0005);
        } else if (byteNum < MemoryConstants.GB) {
            return String.format("%.3fMB", (double) byteNum / MemoryConstants.MB + 0.0005);
        } else {
            return String.format("%.3fGB", (double) byteNum / MemoryConstants.GB + 0.0005);
        }
    }