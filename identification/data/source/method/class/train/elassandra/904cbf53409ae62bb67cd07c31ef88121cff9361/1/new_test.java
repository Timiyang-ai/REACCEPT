@Test
    public void testFromXContent() throws IOException {
        QB testQuery = createTestQueryBuilder();
        QueryParseContext context = createParseContext();
        String contentString = testQuery.toString();
        XContentParser parser = XContentFactory.xContent(contentString).createParser(contentString);
        context.reset(parser);
        assertQueryHeader(parser, testQuery.getName());

        QueryBuilder newQuery = queryParserService.queryParser(testQuery.getName()).fromXContent(context);
        assertNotSame(newQuery, testQuery);
        assertEquals(testQuery, newQuery);
        assertEquals(testQuery.hashCode(), newQuery.hashCode());
    }