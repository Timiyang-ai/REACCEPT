    @Test
    public void test_accepts_sling_request() throws HttpCacheRepositoryAccessException {

        Map<String,Object> properties = new HashMap<>();

        properties.put(PROP_REQUEST_URI_PATTERNS, new String[]{"/content/[a-z]{4}.html"});
        activateWithDefaultValues(properties);

        when(request.getRequestURI()).thenReturn("/content/page.html");

        assertTrue(systemUnderTest.accepts(request));

    }