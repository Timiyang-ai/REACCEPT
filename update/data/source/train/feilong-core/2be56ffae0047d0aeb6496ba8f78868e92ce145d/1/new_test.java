@Test
    public void testGetExtension(){
        assertEquals("png", FileUtil.getExtension(fileName1));
        LOGGER.info(fileName1.substring(fileName1.lastIndexOf(".")));
        LOGGER.info(fileName1.substring(fileName1.lastIndexOf("\\") + 1));
    }