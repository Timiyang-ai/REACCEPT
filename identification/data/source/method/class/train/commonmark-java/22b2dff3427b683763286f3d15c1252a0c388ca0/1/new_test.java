    @Test
    public void inlineParser() {
        final InlineParser fakeInlineParser = new InlineParser() {
            @Override
            public void parse(String input, Node node) {
                node.appendChild(new ThematicBreak());
            }
        };

        InlineParserFactory fakeInlineParserFactory = new InlineParserFactory() {

            @Override
            public InlineParser create(InlineParserContext inlineParserContext) {
                return fakeInlineParser;
            }
        };

        Parser parser = Parser.builder().inlineParserFactory(fakeInlineParserFactory).build();
        String input = "**bold** **bold** ~~strikethrough~~";

        assertThat(parser.parse(input).getFirstChild().getFirstChild(), instanceOf(ThematicBreak.class));
    }