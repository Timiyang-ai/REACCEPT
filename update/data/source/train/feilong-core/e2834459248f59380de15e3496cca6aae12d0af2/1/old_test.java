@Test
    public void testIsAssignableFrom(){
        Class<?>[] klsClasses = { "1234".getClass(), "55555".getClass() };
        assertEquals(true, ClassUtils.isAssignable(klsClasses, CharSequence.class));

        assertEquals(true, ClassUtil.isAssignableFrom(Comparable.class, new User().getClass()));
        assertEquals(true, ClassUtil.isAssignableFrom(CharSequence.class, "1234".getClass()));
        assertEquals(true, ClassUtils.isAssignable("1234".getClass(), CharSequence.class));
    }