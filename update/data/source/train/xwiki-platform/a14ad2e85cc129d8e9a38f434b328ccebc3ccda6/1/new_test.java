@Test
    public void testBuildPresentationXDOM() throws Exception
    {
        final DocumentReference reference = new DocumentReference("wiki", "Space", "Page");
        final DocumentModelBridge mockDocumentModelBridge = getMockery().mock(DocumentModelBridge.class);
        final XDOM galleryContent = new XDOM(Collections.<Block> emptyList());
        getMockery().checking(new Expectations()
        {
            {
                oneOf(mockDocumentAccessBridge).getDocument(reference);
                will(returnValue(mockDocumentModelBridge));

                oneOf(mockDocumentModelBridge).getSyntax();
                will(returnValue(Syntax.XWIKI_2_0));

                oneOf(mockXHTMLParser).parse(with(aNonNull(StringReader.class)));
                will(returnValue(galleryContent));

                oneOf(mockDefaultStringEntityReferenceSerializer).serialize(reference);
                will(returnValue("foo"));
            }
        });

        XDOM xdom = presentationBuilder.buildPresentationXDOM("some HTML", reference);
        Assert.assertEquals("foo", xdom.getMetaData().getMetaData(MetaData.BASE));

        List<ExpandedMacroBlock> macros =
            xdom.getBlocks(new ClassBlockMatcher(ExpandedMacroBlock.class), Block.Axes.CHILD);
        Assert.assertEquals(1, macros.size());
        Assert.assertEquals("gallery", macros.get(0).getId());
        Assert.assertEquals(galleryContent, macros.get(0).getChildren().get(0));
    }