@Test
    public void testBuildURL() throws Exception
    {
        getMockery().checking(new Expectations()
        {
            {
                oneOf(documentAccessBridge).getDocumentURL(ATTACHMENT_REFERENCE.getDocumentReference(), "temp", null,
                    null, true);
                will(returnValue("/xwiki/bin/temp/Main/Test"));
            }
        });

        String url = defaultOfficeViewer.buildURL(ATTACHMENT_REFERENCE, "some_temporary_artifact.gif");
        Assert.assertEquals("/xwiki/bin/temp/Main/Test/officeviewer/Test.doc/some_temporary_artifact.gif", url);
    }