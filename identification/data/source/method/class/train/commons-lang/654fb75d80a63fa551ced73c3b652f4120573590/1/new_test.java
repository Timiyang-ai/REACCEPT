    @Test
    public void test_getShortCanonicalName_Class() {
        assertEquals("ClassUtils", ClassUtils.getShortCanonicalName(ClassUtils.class));
        assertEquals("ClassUtils[]", ClassUtils.getShortCanonicalName(ClassUtils[].class));
        assertEquals("ClassUtils[][]", ClassUtils.getShortCanonicalName(ClassUtils[][].class));
        assertEquals("int[]", ClassUtils.getShortCanonicalName(int[].class));
        assertEquals("int[][]", ClassUtils.getShortCanonicalName(int[][].class));

        // Inner types
        class Named {
            // empty
        }
        // WARNING: this is fragile, implementation may change, naming is not guaranteed
        assertEquals("ClassUtilsTest.8", ClassUtils.getShortCanonicalName(new Object() {
            // empty
        }.getClass()));
        // WARNING: this is fragile, implementation may change, naming is not guaranteed
        assertEquals("ClassUtilsTest.8Named", ClassUtils.getShortCanonicalName(Named.class));
        assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortCanonicalName(Inner.class));
    }