    @Test
    public void testXMLEnc() throws IOException {
        String s = "";
        assertSame(s, StringUtil.XMLEnc(s));
        
        s = "asd";
        assertSame(s, StringUtil.XMLEnc(s));
        
        testXMLEnc("a&amp;b&lt;c&gt;d&quot;e&apos;f", "a&b<c>d\"e'f");
        testXMLEnc("&lt;", "<");
        testXMLEnc("&lt;a", "<a");
        testXMLEnc("&lt;a&gt;", "<a>");
        testXMLEnc("a&gt;", "a>");
        testXMLEnc("&lt;&gt;", "<>");
        testXMLEnc("a&lt;&gt;b", "a<>b");
    }