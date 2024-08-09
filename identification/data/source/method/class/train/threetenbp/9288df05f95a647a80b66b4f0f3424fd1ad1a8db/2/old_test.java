@Test(groups={"tck"})
    public void test_getParsed_DateTimeRule_null() throws Exception {
        assertEquals(context.getParsed((DateTimeField) null), null);
    }