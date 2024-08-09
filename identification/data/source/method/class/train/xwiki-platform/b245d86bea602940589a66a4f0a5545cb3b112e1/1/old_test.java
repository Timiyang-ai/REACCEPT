@Test
    public void testGetTemporaryDirectory() throws Exception
    {
        final Container container = getComponentManager().lookup(Container.class);
        final ApplicationContext applicationContext = getMockery().mock(ApplicationContext.class);

        getMockery().checking(new Expectations()
        {
            {
                oneOf(container).getApplicationContext();
                will(returnValue(applicationContext));

                oneOf(applicationContext).getTemporaryDirectory();
                will(returnValue(new File(System.getProperty("java.io.tmpdir"))));
            }
        });

        File tempFile = defaultOfficeViewer.getTemporaryDirectory(ATTACHMENT_REFERENCE);
        Assert.assertTrue(tempFile.getAbsolutePath().endsWith("/temp/officeviewer/xwiki/Main/Test/Test.doc"));
    }