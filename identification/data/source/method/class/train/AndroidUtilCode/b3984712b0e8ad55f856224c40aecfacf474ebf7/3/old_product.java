public static double byte2MemorySize(long byteNum, @MemoryConstants.Unit int unit) {
        if (byteNum < 0) return -1;
        return (double) byteNum / unit;
    }