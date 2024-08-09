    @Test
    public void millis2FitTimeSpan() {
        long millis = 6 * TimeConstants.DAY
                + 6 * TimeConstants.HOUR
                + 6 * TimeConstants.MIN
                + 6 * TimeConstants.SEC
                + 6;
        assertEquals(
                "6天6小时6分钟6秒6毫秒",
                ConvertUtils.millis2FitTimeSpan(millis, 7)
        );
        assertEquals(
                "6天6小时6分钟6秒",
                ConvertUtils.millis2FitTimeSpan(millis, 4)
        );
        assertEquals(
                "6天6小时6分钟",
                ConvertUtils.millis2FitTimeSpan(millis, 3)
        );
        assertEquals(
                "25天24分钟24秒24毫秒",
                ConvertUtils.millis2FitTimeSpan(millis * 4, 5)
        );
    }