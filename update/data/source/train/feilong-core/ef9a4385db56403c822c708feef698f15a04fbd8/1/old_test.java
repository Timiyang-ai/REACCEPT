@Test
    public void testGetResource(){
        LOGGER.debug(ClassLoaderUtil.getResource("") + "");
        LOGGER.debug("" + ClassLoaderUtil.getResource("com"));
        ClassLoaderUtil.getResourceInAllClassLoader("jstl-1.2", this.getClass());
    }