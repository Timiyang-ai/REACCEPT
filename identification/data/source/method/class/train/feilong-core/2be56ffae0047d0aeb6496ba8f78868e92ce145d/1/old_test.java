@Test
    @Ignore
    public void testGetFilePostfixName(){
        assertEquals("png", FileUtil.getFilePostfixName(fileName1));
        LOGGER.info(fileName1.substring(fileName1.lastIndexOf(".")));
        LOGGER.info(fileName1.substring(fileName1.lastIndexOf("\\") + 1));
    }