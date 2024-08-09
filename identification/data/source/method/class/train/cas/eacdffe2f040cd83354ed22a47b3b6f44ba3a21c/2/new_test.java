@Test
    public void testSupports() {
        assertFalse(this.legacyTrustAdaptor.supports(null));
        LegacyCasTrustedCredential goodCred = new LegacyCasTrustedCredential();
        assertTrue(this.legacyTrustAdaptor.supports(goodCred));
        LegacyCasCredential badCred = new LegacyCasCredential();
        assertFalse(this.legacyTrustAdaptor.supports(badCred));
    }