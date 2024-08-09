@Test
    public void testFindResource() throws Exception
    {
        URIClassLoader cl =
            new URIClassLoader(new URI[] {new URI("attachmentjar://page%40filename1"), new URI("http://some/url"),
            new URI("attachmentjar://filename2")}, getComponentManager().lookup(URLStreamHandlerFactory.class));

        Assert.assertEquals(3, cl.getURLs().length);
        Assert.assertEquals("attachmentjar://page%40filename1", cl.getURLs()[0].toString());
        Assert.assertEquals("http://some/url", cl.getURLs()[1].toString());
        Assert.assertEquals("attachmentjar://filename2", cl.getURLs()[2].toString());

        final AttachmentReference attachmentName1 = new AttachmentReference("filename1",
            new DocumentReference("wiki", "space", "page"));
        final AttachmentReference attachmentName2 = new AttachmentReference("filename2",
            new DocumentReference("wiki", "space", "page"));

        getMockery().checking(new Expectations()
        {
            {
                allowing(URIClassLoaderTest.this.arf).resolve("page@filename1");
                will(returnValue(attachmentName1));
                oneOf(URIClassLoaderTest.this.dab).getAttachmentContent(attachmentName1);
                will(returnValue(new ByteArrayInputStream(createJarFile("/nomatch"))));
                allowing(URIClassLoaderTest.this.arf).resolve("filename2");
                will(returnValue(attachmentName2));
                oneOf(URIClassLoaderTest.this.dab).getAttachmentContent(attachmentName2);
                will(returnValue(new ByteArrayInputStream(createJarFile("/something"))));
            }
        });

        Assert.assertEquals("jar:attachmentjar://filename2!/something", cl.findResource("/something").toString());
    }