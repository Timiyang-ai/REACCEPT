@Test(expectedExceptions=NullPointerException.class, groups={"tck"})
    public void test_appendZoneText_1arg_nullText() throws Exception {
        builder.appendZoneText(null);
    }