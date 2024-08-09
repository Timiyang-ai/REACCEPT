@Test(dataProvider="sampleTimes", groups={"implementation"})
    public void test_toLocalTime(int h, int m, int s, int ns) {
        LocalTime t = LocalTime.of(h, m, s, ns);
        LocalDateTime dt = LocalDateTime.of(LocalDate.of(2011, 7, 30), t);
        assertSame(dt.toLocalTime(), t);
    }