    @Test
    public void testFTLStringLiteralDec() throws ParseException {
        assertEquals("", StringUtil.FTLStringLiteralDec(""));
        assertEquals("x", StringUtil.FTLStringLiteralDec("x"));
        assertEquals("\nq", StringUtil.FTLStringLiteralDec("\\x0Aq"));
        assertEquals("\n\r1", StringUtil.FTLStringLiteralDec("\\x0A\\x000D1"));
        assertEquals("\n\r\t", StringUtil.FTLStringLiteralDec("\\n\\r\\t"));
        assertEquals("${1}#{2}[=3]", StringUtil.FTLStringLiteralDec("$\\{1}#\\{2}[\\=3]"));
        assertEquals("{=", StringUtil.FTLStringLiteralDec("\\{\\="));
        assertEquals("\\=", StringUtil.FTLStringLiteralDec("\\\\="));
           
        try {
            StringUtil.FTLStringLiteralDec("\\[");
            fail();
        } catch (ParseException e) {
            assertThat(e.getMessage(), containsString("\\["));
        }
    }