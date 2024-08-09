@Test
    public void testParseHints_File() throws Exception {
        File file = BaseTest.getResourceAsFile(this, "hints.xml");
        HintParser instance = new HintParser();
        Hints results = instance.parseHints(file);
        assertEquals("Two duplicating hints should have been read", 2, results.getVendorDuplicatingHintRules().size());
        assertEquals("Two hint rules should have been read", 2, results.getHintRules().size());
    }