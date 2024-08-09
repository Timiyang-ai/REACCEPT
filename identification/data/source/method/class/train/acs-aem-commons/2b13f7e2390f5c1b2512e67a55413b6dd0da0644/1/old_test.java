    @Test
    public void test_put() throws HttpCacheDataStreamException, IOException {
        CacheKey key = mock(CacheKey.class);
        CacheContent content = mock(CacheContent.class);
        InputStream inputStream = getClass().getResourceAsStream("cachecontent.html");
        when(content.getInputDataStream()).thenReturn(inputStream);
        systemUnderTest.put(key,content);

        assertTrue("contains entry we just put in",systemUnderTest.contains(key));

        assertEquals(1, systemUnderTest.size());

        CacheContent retrievedContent = systemUnderTest.getIfPresent(key);
        String retrievedContentString = IOUtils.toString(retrievedContent.getInputDataStream(), StandardCharsets.UTF_8);
        String expectedContentString = IOUtils.toString(getClass().getResourceAsStream("cachecontent.html"), StandardCharsets.UTF_8);

        assertEquals(expectedContentString, retrievedContentString);
    }