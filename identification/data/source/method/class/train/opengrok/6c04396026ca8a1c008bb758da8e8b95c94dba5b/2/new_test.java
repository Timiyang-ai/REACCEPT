    @Test
    public void URIEncodePath() {
        assertEquals("", Util.URIEncodePath(""));
        assertEquals("/", Util.URIEncodePath("/"));
        assertEquals("a", Util.URIEncodePath("a"));
        assertEquals("%09", Util.URIEncodePath("\t"));
        assertEquals("a%2Bb", Util.URIEncodePath("a+b"));
        assertEquals("a%20b", Util.URIEncodePath("a b"));
        assertEquals("/a//x/yz/%23%23/%20/%20%3F",
                Util.URIEncodePath("/a//x/yz/##/ / ?"));
        assertEquals("foo%3A%3Abar%3A%3Atest.js",
                Util.URIEncodePath("foo::bar::test.js"));
        assertEquals("bl%C3%A5b%C3%A6rsyltet%C3%B8y",
                Util.URIEncodePath("bl\u00E5b\u00E6rsyltet\u00F8y"));
    }