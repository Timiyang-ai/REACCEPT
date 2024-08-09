@Test
    public void testExecute() throws Exception
    {
        registerWikiMacro("wikimacro1", "This is **bold**");

        Converter converter = getComponentManager().lookup(Converter.class);

        DefaultWikiPrinter printer = new DefaultWikiPrinter();
        converter.convert(new StringReader("{{wikimacro1 param1=\"value1\" param2=\"value2\"/}}"), Syntax.XWIKI_2_0,
            Syntax.XHTML_1_0, printer);

        // Note: We're using XHTML as the output syntax just to make it easy for asserting.
        Assert.assertEquals("<p>This is <strong>bold</strong></p>", printer.toString());
    }