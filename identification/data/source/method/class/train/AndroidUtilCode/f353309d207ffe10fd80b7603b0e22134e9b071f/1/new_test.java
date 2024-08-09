    @Test
    public void reflect() {
        Assert.assertEquals(
                ReflectUtils.reflect(Object.class),
                ReflectUtils.reflect("java.lang.Object", ClassLoader.getSystemClassLoader())
        );
        assertEquals(
                ReflectUtils.reflect(Object.class),
                ReflectUtils.reflect("java.lang.Object")
        );
        assertEquals(
                ReflectUtils.reflect(String.class).get(),
                ReflectUtils.reflect("java.lang.String").get()
        );
        assertEquals(
                Object.class,
                ReflectUtils.reflect(Object.class).get()
        );
        assertEquals(
                "abc",
                ReflectUtils.reflect((Object) "abc").get()
        );
        assertEquals(
                1,
                ReflectUtils.reflect(1).get()
        );
    }