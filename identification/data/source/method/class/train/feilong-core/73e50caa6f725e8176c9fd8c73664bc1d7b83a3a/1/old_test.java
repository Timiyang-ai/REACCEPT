@Test
    public void testGetResource232(){
        assertEquals(
                        "file:/E:/Workspaces/feilong/feilong-core/target/classes/com/feilong/core/lang/ArrayUtil.class",
                        ClassLoaderUtil.getResourceInAllClassLoader("com/feilong/core/lang/ArrayUtil.class", this.getClass()).toString());
    }