    @Test
    public void getCacheKey() {
        assertEquals(
                "http://example.com",
                new UrlParseRequest(Method.GET, "http://example.com").getCacheKey());
        assertEquals(
                "http://example.com",
                new UrlParseRequest(Method.DEPRECATED_GET_OR_POST, "http://example.com")
                        .getCacheKey());
        assertEquals(
                "1-http://example.com",
                new UrlParseRequest(Method.POST, "http://example.com").getCacheKey());
        assertEquals(
                "2-http://example.com",
                new UrlParseRequest(Method.PUT, "http://example.com").getCacheKey());
    }