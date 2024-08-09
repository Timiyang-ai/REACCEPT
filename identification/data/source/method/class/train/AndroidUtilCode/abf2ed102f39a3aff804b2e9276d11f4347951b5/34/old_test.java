    @Test
    public void getNowString() {
        assertEquals(System.currentTimeMillis(), TimeUtils.string2Millis(TimeUtils.getNowString()), delta);
        assertEquals(System.currentTimeMillis(), TimeUtils.string2Millis(TimeUtils.getNowString(mFormat), mFormat), delta);
    }