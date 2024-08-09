public static final int getIntervalHour(long spaceMillisecond){
        // 相差小时数
        return (int) (spaceMillisecond / (TimeInterval.SECONDS_PER_HOUR * 1000));
    }