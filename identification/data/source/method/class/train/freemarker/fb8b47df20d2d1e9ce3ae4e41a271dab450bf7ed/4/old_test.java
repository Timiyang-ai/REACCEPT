    @Test
    public void testXMLEncNQG() throws IOException {
        String s = "";
        assertSame(s, StringUtil.XMLEncNQG(s));
        
        s = "asd";
        assertSame(s, StringUtil.XMLEncNQG(s));
        
        assertEquals("a&amp;b&lt;c>d\"e'f", StringUtil.XMLEncNQG("a&b<c>d\"e'f"));
        assertEquals("&lt;", StringUtil.XMLEncNQG("<"));
        assertEquals("&lt;a", StringUtil.XMLEncNQG("<a"));
        assertEquals("&lt;a>", StringUtil.XMLEncNQG("<a>"));
        assertEquals("a>", StringUtil.XMLEncNQG("a>"));
        assertEquals("&lt;>", StringUtil.XMLEncNQG("<>"));
        assertEquals("a&lt;>b", StringUtil.XMLEncNQG("a<>b"));
        
        assertEquals("&gt;", StringUtil.XMLEncNQG(">"));
        assertEquals("]&gt;", StringUtil.XMLEncNQG("]>"));
        assertEquals("]]&gt;", StringUtil.XMLEncNQG("]]>"));
        assertEquals("x]]&gt;", StringUtil.XMLEncNQG("x]]>"));
        assertEquals("x]>", StringUtil.XMLEncNQG("x]>"));
        assertEquals("]x>", StringUtil.XMLEncNQG("]x>"));
    }