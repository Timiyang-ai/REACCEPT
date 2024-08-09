    @Test
    public void test_isInnerClass_Class() {
        assertTrue(ClassUtils.isInnerClass(Inner.class));
        assertTrue(ClassUtils.isInnerClass(Map.Entry.class));
        assertTrue(ClassUtils.isInnerClass(new Cloneable() {
            // empty
        }.getClass()));
        assertFalse(ClassUtils.isInnerClass(this.getClass()));
        assertFalse(ClassUtils.isInnerClass(String.class));
        assertFalse(ClassUtils.isInnerClass(null));
    }