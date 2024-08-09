@Test
    public void testSupports() {
        assertTrue(this.credentialsBinder.supports(LegacyCasCredentials.class));
        assertTrue(this.credentialsBinder.supports(LegacyCasTrustedCredentials.class));
        assertFalse(this.credentialsBinder.supports(AdHocUnsupportedCredentials.class));
    }