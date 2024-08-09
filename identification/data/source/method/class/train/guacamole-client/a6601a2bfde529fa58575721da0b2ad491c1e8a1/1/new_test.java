@Test
    public void testCanonicalize() {
        assertEquals("A", TokenName.canonicalize("a"));
        assertEquals("B", TokenName.canonicalize("b"));
        assertEquals("1", TokenName.canonicalize("1"));
        assertEquals("SOME_URL", TokenName.canonicalize("someURL"));
        assertEquals("LOWERCASE_WITH_DASHES", TokenName.canonicalize("lowercase-with-dashes"));
        assertEquals("HEADLESS_CAMEL_CASE", TokenName.canonicalize("headlessCamelCase"));
        assertEquals("CAMEL_CASE", TokenName.canonicalize("CamelCase"));
        assertEquals("CAMEL_CASE", TokenName.canonicalize("CamelCase"));
        assertEquals("LOWERCASE_WITH_UNDERSCORES", TokenName.canonicalize("lowercase_with_underscores"));
        assertEquals("UPPERCASE_WITH_UNDERSCORES", TokenName.canonicalize("UPPERCASE_WITH_UNDERSCORES"));
        assertEquals("A_VERY_INCONSISTENT_MIX_OF_ALL_STYLES", TokenName.canonicalize("aVery-INCONSISTENTMix_ofAllStyles"));
        assertEquals("ABC_123_DEF_456", TokenName.canonicalize("abc123def456"));
        assertEquals("ABC_123_DEF_456", TokenName.canonicalize("ABC123DEF456"));
        assertEquals("WORD_A_WORD_AB_WORD_ABC_WORD", TokenName.canonicalize("WordAWordABWordABCWord"));
        
        assertEquals("AUTH_ATTRIBUTE", TokenName.canonicalize("Attribute", "AUTH_"));
        assertEquals("auth_SOMETHING", TokenName.canonicalize("Something", "auth_"));
    }