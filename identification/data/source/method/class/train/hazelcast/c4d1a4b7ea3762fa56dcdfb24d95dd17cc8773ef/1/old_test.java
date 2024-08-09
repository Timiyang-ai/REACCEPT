@Test(expected = IllegalArgumentException.class)
    public void test_checkInMemoryFormat_NATIVE() {
        checkInMemoryFormat(InMemoryFormat.NATIVE);
    }