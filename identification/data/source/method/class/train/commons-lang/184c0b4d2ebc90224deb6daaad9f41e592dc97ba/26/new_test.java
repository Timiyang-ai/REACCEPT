    @Test
    public void test_getPackageName_Class() {
        assertEquals("java.lang", ClassUtils.getPackageName(String.class));
        assertEquals("java.util", ClassUtils.getPackageName(Map.Entry.class));
        assertEquals("", ClassUtils.getPackageName((Class<?>) null));

        // LANG-535
        assertEquals("java.lang", ClassUtils.getPackageName(String[].class));

        // Primitive Arrays
        assertEquals("", ClassUtils.getPackageName(boolean[].class));
        assertEquals("", ClassUtils.getPackageName(byte[].class));
        assertEquals("", ClassUtils.getPackageName(char[].class));
        assertEquals("", ClassUtils.getPackageName(short[].class));
        assertEquals("", ClassUtils.getPackageName(int[].class));
        assertEquals("", ClassUtils.getPackageName(long[].class));
        assertEquals("", ClassUtils.getPackageName(float[].class));
        assertEquals("", ClassUtils.getPackageName(double[].class));

        // Arrays of arrays of ...
        assertEquals("java.lang", ClassUtils.getPackageName(String[][].class));
        assertEquals("java.lang", ClassUtils.getPackageName(String[][][].class));
        assertEquals("java.lang", ClassUtils.getPackageName(String[][][][].class));

        // On-the-fly types
        class Named {
            // empty
        }
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageName(new Object() {
            // empty
        }.getClass()));
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageName(Named.class));
    }