    @Test
    public void enabledBlockTypes() {
        String given = "# heading 1\n\nnot a heading";

        Parser parser = Parser.builder().build(); // all core parsers by default
        Node document = parser.parse(given);
        assertThat(document.getFirstChild(), instanceOf(Heading.class));

        Set<Class<? extends Block>> headersOnly = new HashSet<>();
        headersOnly.add(Heading.class);
        parser = Parser.builder().enabledBlockTypes(headersOnly).build();
        document = parser.parse(given);
        assertThat(document.getFirstChild(), instanceOf(Heading.class));

        Set<Class<? extends Block>> noCoreTypes = new HashSet<>();
        parser = Parser.builder().enabledBlockTypes(noCoreTypes).build();
        document = parser.parse(given);
        assertThat(document.getFirstChild(), not(instanceOf(Heading.class)));
    }