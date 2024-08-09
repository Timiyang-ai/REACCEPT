@Test
    public void testContainsUsedString() {
        String str = "apache";
        String str2 = "owasp";
        Dependency instance = new Dependency();
        instance.getVendorEvidence().addEvidence("manifest", "something", "apache", Evidence.Confidence.HIGH);
        instance.getVendorEvidence().addEvidence("manifest", "something", "owasp", Evidence.Confidence.MEDIUM);
        assertFalse(instance.containsUsedString(str));
        assertFalse(instance.containsUsedString(str2));
        for (Evidence i : instance.getVendorEvidence().iterator(Evidence.Confidence.HIGH)) {
            String readValue = i.getValue();
        }
        assertTrue(instance.containsUsedString(str));
        assertFalse(instance.containsUsedString(str2));
        for (Evidence i : instance.getVendorEvidence().iterator(Evidence.Confidence.MEDIUM)) {
            String readValue = i.getValue();
        }
        assertTrue(instance.containsUsedString(str));
        assertTrue(instance.containsUsedString(str2));
    }