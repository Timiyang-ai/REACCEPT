    @Test
    public void getMillisByNow() {
        assertEquals(System.currentTimeMillis() + TimeConstants.DAY, TimeUtils.getMillisByNow(1, TimeConstants.DAY), delta);
    }