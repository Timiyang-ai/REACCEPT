@Test
    public void testGetResource232(){
        String path = "file:/E:/Workspaces/feilong/feilong-core/target/classes/com/feilong/core/lang/ArrayUtil.class";
        String resourceName = "com/feilong/core/lang/ArrayUtil.class";
        assertEquals(path, ClassLoaderUtil.getResourceInAllClassLoader(resourceName, this.getClass()).toString());
        assertEquals(path, ClassLoaderUtil.getResourceInAllClassLoader("/" + resourceName, this.getClass()).toString());
    }