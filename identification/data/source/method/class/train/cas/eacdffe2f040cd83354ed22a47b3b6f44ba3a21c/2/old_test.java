@Test
    public void testSupports() {
        assertFalse(this.legacyTrustAdaptor.supports(null));
        LegacyCasTrustedCredentials goodCred = new LegacyCasTrustedCredentials();
        assertTrue(this.legacyTrustAdaptor.supports(goodCred));
        LegacyCasCredentials badCred = new LegacyCasCredentials();
        assertFalse(this.legacyTrustAdaptor.supports(badCred));
    }