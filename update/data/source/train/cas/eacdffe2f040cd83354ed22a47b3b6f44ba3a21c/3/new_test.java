@Test
    public void testAuthenticate() {
        LegacyCasTrustedCredential trustedCredentials = new LegacyCasTrustedCredential();

        ServletRequest request = new MockHttpServletRequest();

        trustedCredentials.setServletRequest(request);

        MockTrustHandler mockTrustHandler = new MockTrustHandler();

        mockTrustHandler.setUserName("testUser");

        this.legacyTrustAdaptor.setTrustHandler(mockTrustHandler);

        assertTrue(this.legacyTrustAdaptor.authenticate(trustedCredentials));

        assertSame(request, mockTrustHandler.getRequest());

    }