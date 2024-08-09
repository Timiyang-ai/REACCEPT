@Test
    public void testGetResource(){
        LOGGER.debug(ClassLoaderUtil.getResource("") + "");
        LOGGER.debug("" + ClassLoaderUtil.getResource("com"));

    }