    @Test
    public void testXMLEncQAttr() throws IOException {
        String s = "";
        assertSame(s, StringUtil.XMLEncQAttr(s));
        
        s = "asd";
        assertSame(s, StringUtil.XMLEncQAttr(s));
        
        assertEquals("a&amp;b&lt;c>d&quot;e'f", StringUtil.XMLEncQAttr("a&b<c>d\"e'f"));
        assertEquals("&lt;", StringUtil.XMLEncQAttr("<"));
        assertEquals("&lt;a", StringUtil.XMLEncQAttr("<a"));
        assertEquals("&lt;a>", StringUtil.XMLEncQAttr("<a>"));
        assertEquals("a>", StringUtil.XMLEncQAttr("a>"));
        assertEquals("&lt;>", StringUtil.XMLEncQAttr("<>"));
        assertEquals("a&lt;>b", StringUtil.XMLEncQAttr("a<>b"));
    }