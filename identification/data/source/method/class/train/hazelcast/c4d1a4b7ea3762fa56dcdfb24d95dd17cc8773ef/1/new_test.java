@Test(expected = IllegalArgumentException.class)
    public void test_checkNotNative_NATIVE() {
        checkNotNative(InMemoryFormat.NATIVE);
    }