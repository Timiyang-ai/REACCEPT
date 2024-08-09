    @Test
    public void getBaseURL_returnsServiceEndpointIfSet() throws MalformedURLException {
        Map<String, String> configurations = new HashMap<String, String>();
        configurations.put(Constants.ENDPOINT, "https://www.example.com/abc");
        RESTAPICallPreHandler restapiCallPreHandler = new RESTAPICallPreHandler(configurations);

        URL result = restapiCallPreHandler.getBaseURL();

        assertNotNull(result);
        assertEquals(result.getHost(), "www.example.com");
        assertEquals(result.getPath(), "/abc/");
    }