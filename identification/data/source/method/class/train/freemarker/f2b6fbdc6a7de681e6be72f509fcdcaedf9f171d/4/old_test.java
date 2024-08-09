    @Test
    public void testXHTMLEnc() throws IOException {
        String s = "";
        assertSame(s, StringUtil.XHTMLEnc(s));
        
        s = "asd";
        assertSame(s, StringUtil.XHTMLEnc(s));
        
        testXHTMLEnc("a&amp;b&lt;c&gt;d&quot;e&#39;f", "a&b<c>d\"e'f");
        testXHTMLEnc("&lt;", "<");
        testXHTMLEnc("&lt;a", "<a");
        testXHTMLEnc("&lt;a&gt;", "<a>");
        testXHTMLEnc("a&gt;", "a>");
        testXHTMLEnc("&lt;&gt;", "<>");
        testXHTMLEnc("a&lt;&gt;b", "a<>b");
    }