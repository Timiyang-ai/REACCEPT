@Test(timeOut=30000)  // TODO: remove when time zone loading is faster
    public void nowSystemClock() {
        LocalDateTime expected = LocalDateTime.now(Clock.systemDefaultZone());
        LocalDateTime test = LocalDateTime.nowSystemClock();
        long diff = Math.abs(test.toLocalTime().toNanoOfDay() - expected.toLocalTime().toNanoOfDay());
        if (diff >= 100000000) {
            // may be date change
            expected = LocalDateTime.now(Clock.systemDefaultZone());
            test = LocalDateTime.nowSystemClock();
            diff = Math.abs(test.toLocalTime().toNanoOfDay() - expected.toLocalTime().toNanoOfDay());
        }
        assertTrue(diff < 100000000);  // less than 0.1 secs
    }