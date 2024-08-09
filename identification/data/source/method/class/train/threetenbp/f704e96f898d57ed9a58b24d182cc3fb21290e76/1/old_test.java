@Test(dataProvider="toStringAndParse")
    public void test_parse(Period test, String expected) {
    	assertEquals(test, Period.parse(expected));
    }