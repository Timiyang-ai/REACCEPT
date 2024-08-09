@Test
    public void testBuildPresentationXDOM()
    {
        final DocumentReference reference = new DocumentReference("wiki", "Space", "Page");
        final DocumentModelBridge mockDocumentModelBridge = getMockery().mock(DocumentModelBridge.class);
        final XDOM galleryContent = new XDOM(Collections.<Block> emptyList());
        getMockery().checking(new Expectations()
        {
            {
                try {
                    oneOf(mockDocumentAccessBridge).getDocument(reference);
                    will(returnValue(mockDocumentModelBridge));
                } catch (Exception e) {
                    Assert.fail(e.getMessage());
                }

                oneOf(mockDocumentModelBridge).getSyntax();
                will(returnValue(Syntax.XWIKI_2_0));

                try {
                    oneOf(mockXHTMLParser).parse(with(aNonNull(StringReader.class)));
                    will(returnValue(galleryContent));
                } catch (ParseException e) {
                    Assert.fail(e.getMessage());
                }
            }
        });

        XDOM xdom = null;
        try {
            xdom = presentationBuilder.buildPresentationXDOM("some HTML", reference);
        } catch (OfficeImporterException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(xdom);

        List<ExpandedMacroBlock> macros =
            xdom.getBlocks(new ClassBlockMatcher(ExpandedMacroBlock.class), Block.Axes.CHILD);
        Assert.assertEquals(1, macros.size());
        Assert.assertEquals("gallery", macros.get(0).getId());
        Assert.assertEquals(galleryContent, macros.get(0).getChildren().get(0));
    }