@Test
    public void test_isAssignable_ClassArray_ClassArray() throws Exception {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        assertFalse(ClassUtils.isAssignable(array1, array2));
        assertFalse(ClassUtils.isAssignable(null, array2));
        assertTrue(ClassUtils.isAssignable(null, array0));
        assertTrue(ClassUtils.isAssignable(array0, array0));
//        assertTrue(ClassUtils.isAssignable(array0, null)); 
        assertTrue(ClassUtils.isAssignable(array0, (Class<?>[]) null)); // explicit cast to avoid warning
        assertTrue(ClassUtils.isAssignable((Class[]) null, (Class[]) null));

        assertFalse(ClassUtils.isAssignable(array1, array1s));
        assertTrue(ClassUtils.isAssignable(array1s, array1s));
        assertTrue(ClassUtils.isAssignable(array1s, array1));

        final boolean autoboxing = SystemUtils.isJavaVersionAtLeast(JAVA_1_5);

        assertEquals(autoboxing, ClassUtils.isAssignable(arrayPrimitives, arrayWrappers));
        assertEquals(autoboxing, ClassUtils.isAssignable(arrayWrappers, arrayPrimitives));
        assertFalse(ClassUtils.isAssignable(arrayPrimitives, array1));
        assertFalse(ClassUtils.isAssignable(arrayWrappers, array1));
        assertEquals(autoboxing, ClassUtils.isAssignable(arrayPrimitives, array2));
        assertTrue(ClassUtils.isAssignable(arrayWrappers, array2));
    }