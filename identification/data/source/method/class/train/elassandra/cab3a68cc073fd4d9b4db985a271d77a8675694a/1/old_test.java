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
        assertEquals("Queries should be equal: " + newQuery + " vs. " + testQuery, newQuery, testQuery);
        assertEquals("Queries should have equal hashcodes: " + newQuery + " vs. " + testQuery, newQuery.hashCode(), testQuery.hashCode());
    }