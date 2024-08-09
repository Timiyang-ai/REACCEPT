@Test
    public void testGetTemporaryFile()
    {
        getContext().setDatabase("wiki");
        Assert.assertNotNull(action.getTemporaryFile("/xwiki/bin/temp/Space/Page/module/file.txt", getContext()));
    }