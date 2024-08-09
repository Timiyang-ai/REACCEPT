@Test
    public void toHTML() throws Exception
    {
        String source = "wiki syntax";
        String syntaxId = "syntax/x.y";

        // The source should be parsed.
        Parser parser = this.mocker.registerMockComponent(Parser.class, syntaxId);

        XDOM xdom = new XDOM(Collections.<Block>emptyList());
        when(parser.parse(any(StringReader.class))).thenReturn(xdom);

        Assert.assertEquals("", mocker.getComponentUnderTest().toHTML(source, syntaxId));

        // Verify that the macro transformations have been executed.
        Transformation macroTransformation = mocker.getInstance(Transformation.class, "macro");
        RenderingContext renderingContext = mocker.getInstance(RenderingContext.class);

        // It's very important to verify that a transformation context id is set as otherwise if the content being
        // edited has different velocity macros executing, they'll be executed in isolation and thus what's defined in
        // one won't be visible from the other ones (For example see https://jira.xwiki.org/browse/XWIKI-11695).
        ArgumentCaptor<TransformationContext> txContextArgument = ArgumentCaptor.forClass(TransformationContext.class);
        verify((MutableRenderingContext) renderingContext).transformInContext(same(macroTransformation),
            txContextArgument.capture(), same(xdom));
        assertEquals("wysiwygtxid", txContextArgument.getValue().getId());

        // Verify the XDOM is rendered to Annotated XHTML.
        BlockRenderer xhtmlRenderer = mocker.getInstance(BlockRenderer.class, "annotatedxhtml/1.0");
        verify(xhtmlRenderer).render(same(xdom), any(WikiPrinter.class));
    }