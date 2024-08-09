@Test(dataProvider="sampleToString")
    public void test_parse(int y, int month, int d, int h, int m, int s, int n, String offsetId, String text) {
        OffsetDateTime t = OffsetDateTime.parse(text);
        assertEquals(t.getYear(), y);
        assertEquals(t.getMonth().getValue(), month);
        assertEquals(t.getDayOfMonth(), d);
        assertEquals(t.getHour(), h);
        assertEquals(t.getMinute(), m);
        assertEquals(t.getSecond(), s);
        assertEquals(t.getNano(), n);
        assertEquals(t.getOffset().getId(), offsetId);
    }