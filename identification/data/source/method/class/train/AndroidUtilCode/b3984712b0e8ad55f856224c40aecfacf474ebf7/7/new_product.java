@SuppressLint("DefaultLocale")
    public static String byte2FitMemorySize(long byteNum) {
        if (byteNum < 0) {
            return "shouldn't be less than zero!";
        } else if (byteNum < MemoryConstant.KB) {
            return String.format("%.3fB", (double) byteNum + 0.0005);
        } else if (byteNum < MemoryConstant.MB) {
            return String.format("%.3fKB", (double) byteNum / MemoryConstant.KB + 0.0005);
        } else if (byteNum < MemoryConstant.GB) {
            return String.format("%.3fMB", (double) byteNum / MemoryConstant.MB + 0.0005);
        } else {
            return String.format("%.3fGB", (double) byteNum / MemoryConstant.GB + 0.0005);
        }
    }