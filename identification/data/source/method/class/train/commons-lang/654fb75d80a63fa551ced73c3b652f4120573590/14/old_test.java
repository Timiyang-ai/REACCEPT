    @Test
    public void test_getCanonicalName_Class() {
        assertEquals("org.apache.commons.lang3.ClassUtils", ClassUtils.getCanonicalName(ClassUtils.class));
        assertEquals("java.util.Map.Entry", ClassUtils.getCanonicalName(Map.Entry.class));
        assertEquals("", ClassUtils.getCanonicalName((Class<?>) null));

        assertEquals("java.lang.String[]", ClassUtils.getCanonicalName(String[].class));
        assertEquals("java.util.Map.Entry[]", ClassUtils.getCanonicalName(Map.Entry[].class));

        // Primitives
        assertEquals("boolean", ClassUtils.getCanonicalName(boolean.class));
        assertEquals("byte", ClassUtils.getCanonicalName(byte.class));
        assertEquals("char", ClassUtils.getCanonicalName(char.class));
        assertEquals("short", ClassUtils.getCanonicalName(short.class));
        assertEquals("int", ClassUtils.getCanonicalName(int.class));
        assertEquals("long", ClassUtils.getCanonicalName(long.class));
        assertEquals("float", ClassUtils.getCanonicalName(float.class));
        assertEquals("double", ClassUtils.getCanonicalName(double.class));

        // Primitive Arrays
        assertEquals("boolean[]", ClassUtils.getCanonicalName(boolean[].class));
        assertEquals("byte[]", ClassUtils.getCanonicalName(byte[].class));
        assertEquals("char[]", ClassUtils.getCanonicalName(char[].class));
        assertEquals("short[]", ClassUtils.getCanonicalName(short[].class));
        assertEquals("int[]", ClassUtils.getCanonicalName(int[].class));
        assertEquals("long[]", ClassUtils.getCanonicalName(long[].class));
        assertEquals("float[]", ClassUtils.getCanonicalName(float[].class));
        assertEquals("double[]", ClassUtils.getCanonicalName(double[].class));

        // Arrays of arrays of ...
        assertEquals("java.lang.String[][]", ClassUtils.getCanonicalName(String[][].class));
        assertEquals("java.lang.String[][][]", ClassUtils.getCanonicalName(String[][][].class));
        assertEquals("java.lang.String[][][][]", ClassUtils.getCanonicalName(String[][][][].class));

        // Inner types
        class Named {
            // empty
        }
        assertEquals(StringUtils.EMPTY, ClassUtils.getCanonicalName(new Object() {
            // empty
        }.getClass()));
        assertEquals(StringUtils.EMPTY, ClassUtils.getCanonicalName(Named.class));
        assertEquals("org.apache.commons.lang3.ClassUtilsTest.Inner", ClassUtils.getCanonicalName(Inner.class));
    }