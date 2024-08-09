@Test
    public void testCimDateTimeToDate() {
        assertEquals(ParseUtil.cimDateTimeToDate("20160513072950.782000-420").getTime(), 1463149790782L);
    }