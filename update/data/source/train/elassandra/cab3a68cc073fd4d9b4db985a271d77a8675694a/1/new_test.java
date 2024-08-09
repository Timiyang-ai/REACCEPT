@Test
    public void testFromXContent() throws IOException {
        QB testQuery = createTestQueryBuilder();
        QueryParseContext context = createContext();
        String contentString = testQuery.toString();
        XContentParser parser = XContentFactory.xContent(contentString).createParser(contentString);
        context.reset(parser);
        assertQueryHeader(parser, testQuery.getName());

        QueryBuilder newQuery = queryParserService.queryParser(testQuery.getName()).fromXContent(context);
        assertNotSame(newQuery, testQuery);
        assertEquals("Queries should be equal", testQuery, newQuery);
        assertEquals("Queries should have equal hashcodes", testQuery.hashCode(), newQuery.hashCode());
    }