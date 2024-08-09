    @Test
    public void parse() {
        Link link = Link.parse("name:alias");
        assertEquals(link.getName(), "name");
        assertEquals(link.getAlias(), "alias");
    }