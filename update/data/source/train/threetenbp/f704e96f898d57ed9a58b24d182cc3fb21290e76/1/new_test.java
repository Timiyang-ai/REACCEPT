@Test(dataProvider="toStringAndParse")
    public void test_parse(Period test, String expected) {
        if (Math.signum(test.getSeconds()) == Math.signum(test.getNanos())) {
            assertEquals(test, Period.parse(expected));
        }
    }