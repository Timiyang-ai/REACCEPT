    public void test_loadDataFromNetwork_returns_a_simple_string() throws Exception {
        // given;
        String loremIpsum = IOUtils.toString(getContext().getResources().openRawResource(R.raw.lorem_ipsum));
        mockWebServer.enqueue(new MockResponse().setBody(loremIpsum));
        mockWebServer.play();

        // when
        SimpleTextRequest loremIpsumTextRequest = new SimpleTextRequest(mockWebServer.getUrl("/").toString());
        String stringReturned = loremIpsumTextRequest.loadDataFromNetwork();

        // then
        assertTrue(stringReturned.startsWith("Lorem ipsum"));
    }