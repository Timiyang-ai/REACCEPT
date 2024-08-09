public static final int getIntervalDay(long spaceTime){
        // 相差天数
        return (int) (spaceTime / (TimeInterval.SECONDS_PER_DAY * 1000));
    }