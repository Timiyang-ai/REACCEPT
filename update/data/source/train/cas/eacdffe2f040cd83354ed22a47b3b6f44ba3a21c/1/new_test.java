@Test
    public void testSupports() {
        assertTrue(this.credentialsBinder.supports(LegacyCasCredential.class));
        assertTrue(this.credentialsBinder.supports(LegacyCasTrustedCredential.class));
        assertFalse(this.credentialsBinder.supports(AdHocUnsupportedCredential.class));
    }