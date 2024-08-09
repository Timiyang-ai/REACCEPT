@Test
    public void toHTML() throws Exception
    {
        String source = "wiki syntax";
        Syntax syntax = new Syntax(new SyntaxType("syntax", "Syntax"), "x.y");

        // The source should be parsed.
        ContentParser contentParser = this.mocker.getInstance(ContentParser.class);
        XDOM xdom = new XDOM(Collections.<Block>emptyList());
        when(contentParser.parse(source, syntax, null)).thenReturn(xdom);

        Assert.assertEquals("", mocker.getComponentUnderTest().toHTML(source, syntax.toIdString()));

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