    public void test_valueOf_String1() throws Exception {
        // This threw OutOfMemoryException.
        // http://code.google.com/p/android/issues/detail?id=4185
        assertEquals(2358.166016f, Float.valueOf("2358.166016"));
    }