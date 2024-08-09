@Test
    public void testGetTemporaryFile() throws Exception
    {
        getContext().setDatabase("wiki");
        createEmptyFile("temp/module/wiki/Space/Page/file.txt");
        Assert.assertNotNull(action.getTemporaryFile("/xwiki/bin/temp/Space/Page/module/file.txt", getContext()));
    }