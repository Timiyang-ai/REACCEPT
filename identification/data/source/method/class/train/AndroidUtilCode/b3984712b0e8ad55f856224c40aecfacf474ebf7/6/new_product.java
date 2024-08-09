public static double byte2MemorySize(long byteNum, @MemoryConstant.Unit int unit) {
        if (byteNum < 0) return -1;
        return (double) byteNum / unit;
    }