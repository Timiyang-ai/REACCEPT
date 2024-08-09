    @Test
    public void test_getSimpleName_Class() {
        assertEquals("ClassUtils", ClassUtils.getSimpleName(ClassUtils.class));
        assertEquals("Entry", ClassUtils.getSimpleName(Map.Entry.class));
        assertEquals("", ClassUtils.getSimpleName(null));

        // LANG-535
        assertEquals("String[]", ClassUtils.getSimpleName(String[].class));
        assertEquals("Entry[]", ClassUtils.getSimpleName(Map.Entry[].class));

        // Primitives
        assertEquals("boolean", ClassUtils.getSimpleName(boolean.class));
        assertEquals("byte", ClassUtils.getSimpleName(byte.class));
        assertEquals("char", ClassUtils.getSimpleName(char.class));
        assertEquals("short", ClassUtils.getSimpleName(short.class));
        assertEquals("int", ClassUtils.getSimpleName(int.class));
        assertEquals("long", ClassUtils.getSimpleName(long.class));
        assertEquals("float", ClassUtils.getSimpleName(float.class));
        assertEquals("double", ClassUtils.getSimpleName(double.class));

        // Primitive Arrays
        assertEquals("boolean[]", ClassUtils.getSimpleName(boolean[].class));
        assertEquals("byte[]", ClassUtils.getSimpleName(byte[].class));
        assertEquals("char[]", ClassUtils.getSimpleName(char[].class));
        assertEquals("short[]", ClassUtils.getSimpleName(short[].class));
        assertEquals("int[]", ClassUtils.getSimpleName(int[].class));
        assertEquals("long[]", ClassUtils.getSimpleName(long[].class));
        assertEquals("float[]", ClassUtils.getSimpleName(float[].class));
        assertEquals("double[]", ClassUtils.getSimpleName(double[].class));

        // Arrays of arrays of ...
        assertEquals("String[][]", ClassUtils.getSimpleName(String[][].class));
        assertEquals("String[][][]", ClassUtils.getSimpleName(String[][][].class));
        assertEquals("String[][][][]", ClassUtils.getSimpleName(String[][][][].class));

        // On-the-fly types
        class Named {
            // empty
        }
        assertEquals("", ClassUtils.getSimpleName(new Object() {
            // empty
        }.getClass()));
        assertEquals("Named", ClassUtils.getSimpleName(Named.class));
    }