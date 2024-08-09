@SuppressLint("DefaultLocale")
    public static String byte2FitMemorySize(long byteNum) {
        if (byteNum < 0) {
            return "shouldn't be less than zero!";
        } else if (byteNum < ConstUtils.KB) {
            return String.format("%.3fB", (double) byteNum + 0.0005);
        } else if (byteNum < ConstUtils.MB) {
            return String.format("%.3fKB", (double) byteNum / ConstUtils.KB + 0.0005);
        } else if (byteNum < ConstUtils.GB) {
            return String.format("%.3fMB", (double) byteNum / ConstUtils.MB + 0.0005);
        } else {
            return String.format("%.3fGB", (double) byteNum / ConstUtils.GB + 0.0005);
        }
    }