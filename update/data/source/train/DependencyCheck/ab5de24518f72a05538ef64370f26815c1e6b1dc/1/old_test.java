@Test
    public void testParseHints_File() throws Exception {
        File file = BaseTest.getResourceAsFile(this, "hints.xml");
        HintParser instance = new HintParser();
        Hints results = instance.parseHints(file);
        assertEquals("Two duplicating hints should have been read", 2, results.getVendorDuplicatingHintRules().size());
        assertEquals("Two hint rules should have been read", 2, results.getHintRules().size());

        assertEquals("One add product should have been read", 1, results.getHintRules().get(0).getAddProduct().size());
        assertEquals("One add vendor should have been read", 1, results.getHintRules().get(0).getAddVendor().size());
        assertEquals("Two file name should have been read", 2, results.getHintRules().get(1).getFilenames().size());

        assertEquals("add product name not found", "add product name", results.getHintRules().get(0).getAddProduct().get(0).getName());
        assertEquals("add vendor name not found", "add vendor name", results.getHintRules().get(0).getAddVendor().get(0).getName());
        assertEquals("given product name not found", "given product name", results.getHintRules().get(0).getGivenProduct().get(0).getName());
        assertEquals("given vendor name not found", "given vendor name", results.getHintRules().get(0).getGivenVendor().get(0).getName());

        assertEquals("spring file name not found", "spring", results.getHintRules().get(1).getFilenames().get(0).getValue());
        assertEquals("file name 1 should not be case sensitive", false, results.getHintRules().get(1).getFilenames().get(0).isCaseSensitive());
        assertEquals("file name 1 should not be a regex", false, results.getHintRules().get(1).getFilenames().get(0).isRegex());
        assertEquals("file name 2 should be case sensitive", true, results.getHintRules().get(1).getFilenames().get(1).isCaseSensitive());
        assertEquals("file name 2 should be a regex", true, results.getHintRules().get(1).getFilenames().get(1).isRegex());
               
        assertEquals("sun duplicating vendor", "sun", results.getVendorDuplicatingHintRules().get(0).getValue());
        assertEquals("sun duplicates vendor oracle", "oracle", results.getVendorDuplicatingHintRules().get(0).getDuplicate());
    }