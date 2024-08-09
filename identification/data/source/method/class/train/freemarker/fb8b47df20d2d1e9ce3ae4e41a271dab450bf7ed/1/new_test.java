    @Test
    public void testRTFEnc() throws IOException {
        String s = "";
        assertSame(s, StringUtil.RTFEnc(s));
        
        s = "asd";
        assertSame(s, StringUtil.RTFEnc(s));
        
        testRTFEnc("a\\{b\\}c\\\\d", "a{b}c\\d");
        testRTFEnc("\\{", "{");
        testRTFEnc("\\{a", "{a");
        testRTFEnc("\\{a\\}", "{a}");
        testRTFEnc("a\\}", "a}");
        testRTFEnc("\\{\\}", "{}");
        testRTFEnc("a\\{\\}b", "a{}b");
    }