@Test
    public void test_getInstance() {
        FastDateFormat format1 = FastDateFormat.getInstance();
        FastDateFormat format2 = FastDateFormat.getInstance();
        assertSame(format1, format2);
    }