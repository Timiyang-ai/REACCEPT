@Test
    public void testGetTemporaryDirectory() throws Exception
    {
        final Environment environment = getComponentManager().lookup(Environment.class);

        getMockery().checking(new Expectations()
        {
            {
                oneOf(environment).getTemporaryDirectory();
                will(returnValue(new File(System.getProperty("java.io.tmpdir"))));
            }
        });

        File tempFile = defaultOfficeViewer.getTemporaryDirectory(ATTACHMENT_REFERENCE);
        Assert.assertTrue(tempFile.getAbsolutePath().endsWith("/temp/officeviewer/xwiki/Main/Test/Test.doc"));
    }