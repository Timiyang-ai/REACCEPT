@Test(dataProvider="sampleToString")
    public void test_parse(int h, int m, int s, int n, String offsetId, String text) {
        OffsetTime t = OffsetTime.parse(text);
        assertEquals(t.getHourOfDay(), h);
        assertEquals(t.getMinuteOfHour(), m);
        assertEquals(t.getSecondOfMinute(), s);
        assertEquals(t.getNanoOfSecond(), n);
        assertEquals(t.getOffset().toString(), offsetId);
    }