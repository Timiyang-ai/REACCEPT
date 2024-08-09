@Test
    public void testIsInstance2(){
        assertEquals(true, ClassUtil.isInstance(new User(), new Class<?>[] { Comparable.class, CharSequence.class }));
        assertEquals(false, ClassUtil.isInstance(new User(), new Class<?>[] { Integer.class, CharSequence.class }));
        assertEquals(true, ClassUtil.isInstance("1234", new Class<?>[] { Comparable.class, CharSequence.class }));
    }