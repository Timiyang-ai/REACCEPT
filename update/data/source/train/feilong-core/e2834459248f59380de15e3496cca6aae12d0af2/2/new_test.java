@Test
    public void testIsInterface(){
        assertEquals(false, ClassUtil.isInterface(null));
        assertEquals(false, ClassUtil.isInterface(this.getClass()));
        assertEquals(false, ClassUtil.isInterface(DatePattern.class));
    }