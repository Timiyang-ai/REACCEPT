    @Test
    public void testHTMLEnc() {
        String s = "";
        assertSame(s, StringUtil.HTMLEnc(s));
        
        s = "asd";
        assertSame(s, StringUtil.HTMLEnc(s));
        
        assertEquals("a&amp;b&lt;c&gt;d&quot;e'f", StringUtil.HTMLEnc("a&b<c>d\"e'f"));
        assertEquals("&lt;", StringUtil.HTMLEnc("<"));
        assertEquals("&lt;a", StringUtil.HTMLEnc("<a"));
        assertEquals("&lt;a&gt;", StringUtil.HTMLEnc("<a>"));
        assertEquals("a&gt;", StringUtil.HTMLEnc("a>"));
        assertEquals("&lt;&gt;", StringUtil.HTMLEnc("<>"));
        assertEquals("a&lt;&gt;b", StringUtil.HTMLEnc("a<>b"));
    }