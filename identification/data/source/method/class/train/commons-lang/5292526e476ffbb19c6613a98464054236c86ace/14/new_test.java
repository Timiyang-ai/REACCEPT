@Test
    public void test_getAllSuperclasses_Class() {
        final List<?> list = ClassUtils.getAllSuperclasses(CY.class);
        assertEquals(2, list.size());
        assertEquals(CX.class, list.get(0));
        assertEquals(Object.class, list.get(1));

        assertEquals(null, ClassUtils.getAllSuperclasses(null));
    }