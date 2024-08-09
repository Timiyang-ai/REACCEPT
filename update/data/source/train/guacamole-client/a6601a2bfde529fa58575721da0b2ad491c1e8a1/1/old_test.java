@Test
    public void testFromAttribute() {
        assertEquals("A", TokenName.fromAttribute("a"));
        assertEquals("B", TokenName.fromAttribute("b"));
        assertEquals("1", TokenName.fromAttribute("1"));
        assertEquals("SOME_URL", TokenName.fromAttribute("someURL"));
        assertEquals("LOWERCASE_WITH_DASHES", TokenName.fromAttribute("lowercase-with-dashes"));
        assertEquals("HEADLESS_CAMEL_CASE", TokenName.fromAttribute("headlessCamelCase"));
        assertEquals("CAMEL_CASE", TokenName.fromAttribute("CamelCase"));
        assertEquals("CAMEL_CASE", TokenName.fromAttribute("CamelCase"));
        assertEquals("LOWERCASE_WITH_UNDERSCORES", TokenName.fromAttribute("lowercase_with_underscores"));
        assertEquals("UPPERCASE_WITH_UNDERSCORES", TokenName.fromAttribute("UPPERCASE_WITH_UNDERSCORES"));
        assertEquals("A_VERY_INCONSISTENT_MIX_OF_ALL_STYLES", TokenName.fromAttribute("aVery-INCONSISTENTMix_ofAllStyles"));
        assertEquals("ABC_123_DEF_456", TokenName.fromAttribute("abc123def456"));
        assertEquals("ABC_123_DEF_456", TokenName.fromAttribute("ABC123DEF456"));
        assertEquals("WORD_A_WORD_AB_WORD_ABC_WORD", TokenName.fromAttribute("WordAWordABWordABCWord"));
        
        assertEquals("AUTH_ATTRIBUTE", TokenName.fromAttribute("Attribute", "AUTH_"));
        assertEquals("auth_SOMETHING", TokenName.fromAttribute("Something", "auth_"));
    }