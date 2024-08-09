@Test
    public void testIsAssignableFrom(){
        assertEquals(true, ClassUtil.isAssignableFrom(Comparable.class, new User().getClass()));
        assertEquals(false, ClassUtil.isAssignableFrom(null, new User().getClass()));
        assertEquals(true, ClassUtil.isAssignableFrom(CharSequence.class, "1234".getClass()));
        assertEquals(false, ClassUtil.isAssignableFrom(CharSequence.class, null));
    }