    @Test
    public void testFTLStringLiteralEnc() {
        assertEquals("", StringUtil.FTLStringLiteralEnc(""));
        assertEquals("abc", StringUtil.FTLStringLiteralEnc("abc"));
        assertEquals("{", StringUtil.FTLStringLiteralEnc("{"));
        assertEquals("a{b}c", StringUtil.FTLStringLiteralEnc("a{b}c"));
        assertEquals("a#b", StringUtil.FTLStringLiteralEnc("a#b"));
        assertEquals("a$b", StringUtil.FTLStringLiteralEnc("a$b"));
        assertEquals("a#\\{b}c", StringUtil.FTLStringLiteralEnc("a#{b}c"));
        assertEquals("a$\\{b}c", StringUtil.FTLStringLiteralEnc("a${b}c"));
        assertEquals("a'c\\\"d", StringUtil.FTLStringLiteralEnc("a'c\"d", '"'));
        assertEquals("a\\'c\"d", StringUtil.FTLStringLiteralEnc("a'c\"d", '\''));
        assertEquals("a\\'c\"d", StringUtil.FTLStringLiteralEnc("a'c\"d", '\''));
        assertEquals("\\n\\r\\t\\f\\x0002\\\\", StringUtil.FTLStringLiteralEnc("\n\r\t\f\u0002\\"));
        assertEquals("\\l\\g\\a", StringUtil.FTLStringLiteralEnc("<>&"));
        assertEquals("=[\\=]=", StringUtil.FTLStringLiteralEnc("=[=]="));
        assertEquals("[\\=", StringUtil.FTLStringLiteralEnc("[="));
    }