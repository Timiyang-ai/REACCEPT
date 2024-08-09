@Test
    public void testCimDateTimeToDate() {
        assertEquals(1463149790782L, ParseUtil.cimDateTimeToMillis("20160513072950.782000-420"));
    }