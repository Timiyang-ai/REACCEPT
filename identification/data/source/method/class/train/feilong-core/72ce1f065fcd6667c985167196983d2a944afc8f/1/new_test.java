@Test
    public void testIsInstance2(){
        assertEquals(true, ClassUtil.isInstanceAnyClass(new User(), new Class<?>[] { Comparable.class, CharSequence.class }));
        assertEquals(false, ClassUtil.isInstanceAnyClass(new User(), new Class<?>[] { Integer.class, CharSequence.class }));
        assertEquals(true, ClassUtil.isInstanceAnyClass("1234", new Class<?>[] { Comparable.class, CharSequence.class }));
    }