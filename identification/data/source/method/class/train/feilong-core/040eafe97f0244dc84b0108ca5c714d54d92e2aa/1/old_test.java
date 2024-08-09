@Test
    public void testGetResource(){
        LOGGER.info(ClassLoaderUtil.getResource("") + "");
        LOGGER.info("" + ClassLoaderUtil.getResource("com"));
        ClassLoaderUtil.getResource("jstl-1.2", this.getClass());
    }