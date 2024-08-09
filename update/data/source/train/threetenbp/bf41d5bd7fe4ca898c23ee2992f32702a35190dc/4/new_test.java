@Test(dataProvider="sampleTimes", groups={"tck"})
    public void test_get(int h, int m, int s, int ns) {
        LocalTime a = LocalTime.of(h, m, s, ns);
        assertEquals(a.getHour(), h);
        assertEquals(a.getMinute(), m);
        assertEquals(a.getSecond(), s);
        assertEquals(a.getNano(), ns);
    }