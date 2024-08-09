    @Test
    public void urlEncode_urlDecode() {
        String urlEncodeString = "%E5%93%88%E5%93%88%E5%93%88";
        assertEquals(urlEncodeString, EncodeUtils.urlEncode("哈哈哈"));
        assertEquals(urlEncodeString, EncodeUtils.urlEncode("哈哈哈", "UTF-8"));

        assertEquals("哈哈哈", EncodeUtils.urlDecode(urlEncodeString));
        assertEquals("哈哈哈", EncodeUtils.urlDecode(urlEncodeString, "UTF-8"));
    }