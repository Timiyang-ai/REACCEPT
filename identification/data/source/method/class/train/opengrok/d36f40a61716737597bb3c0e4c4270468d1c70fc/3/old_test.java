    @Test
    public void URIEncode() {
        assertEquals("", Util.URIEncode(""));
        assertEquals("a+b", Util.URIEncode("a b"));
        assertEquals("a%23b", Util.URIEncode("a#b"));
        assertEquals("a%2Fb", Util.URIEncode("a/b"));
        assertEquals("README.txt", Util.URIEncode("README.txt"));
    }