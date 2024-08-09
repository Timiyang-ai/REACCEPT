@Test
    public void testParseHints_File() throws Exception {
        File file = BaseTest.getResourceAsFile(this, "hints.xml");
        HintParser instance = new HintParser();
        instance.parseHints(file);
        HintRule[] hintRules = instance.getHintRules();
        VendorDuplicatingHintRule[] vendorRules = instance.getVendorDuplicatingHintRules();
        assertEquals("Two duplicating hints should have been read", 2, vendorRules.length);
        assertEquals("Two hint rules should have been read", 2, hintRules.length);

        assertEquals("One add product should have been read", 1, hintRules[0].getAddProduct().size());
        assertEquals("One add vendor should have been read", 1, hintRules[0].getAddVendor().size());
        assertEquals("Two file name should have been read", 2, hintRules[1].getFilenames().size());

        assertEquals("add product name not found", "add product name", hintRules[0].getAddProduct().get(0).getName());
        assertEquals("add vendor name not found", "add vendor name", hintRules[0].getAddVendor().get(0).getName());
        assertEquals("given product name not found", "given product name", hintRules[0].getGivenProduct().get(0).getName());
        assertEquals("given vendor name not found", "given vendor name", hintRules[0].getGivenVendor().get(0).getName());

        assertEquals("spring file name not found", "spring", hintRules[1].getFilenames().get(0).getValue());
        assertEquals("file name 1 should not be case sensitive", false, hintRules[1].getFilenames().get(0).isCaseSensitive());
        assertEquals("file name 1 should not be a regex", false, hintRules[1].getFilenames().get(0).isRegex());
        assertEquals("file name 2 should be case sensitive", true, hintRules[1].getFilenames().get(1).isCaseSensitive());
        assertEquals("file name 2 should be a regex", true, hintRules[1].getFilenames().get(1).isRegex());
               
        assertEquals("sun duplicating vendor", "sun", vendorRules[0].getValue());
        assertEquals("sun duplicates vendor oracle", "oracle", vendorRules[0].getDuplicate());
    }