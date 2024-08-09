@Test
    public void testAuthenticate() throws Exception {
        SAMLMessageContext context = new SAMLMessageContext();
        context.setCommunicationProfileId(SAMLConstants.SAML2_WEBSSO_PROFILE_URI);

        SAMLAuthenticationToken token = new SAMLAuthenticationToken(context);
        SAMLCredential result = new SAMLCredential(nameID, assertion, "IDP", "testSP");

        expect(consumer.processAuthenticationResponse(context)).andReturn(result);
        expect(nameID.getValue()).andReturn("Name");

        DateTime expiry = new DateTime().plusHours(4);
        AuthnStatement as = createMock(AuthnStatement.class);
        expect(assertion.getAuthnStatements()).andReturn(Arrays.asList(as)).anyTimes();
        expect(as.getSessionNotOnOrAfter()).andReturn(expiry);

        replay(as);
        replayMock();
        Authentication authentication = provider.authenticate(token);
        assertEquals("Name", authentication.getName());
        assertTrue(authentication instanceof ExpiringUsernameAuthenticationToken);

        ExpiringUsernameAuthenticationToken t = (ExpiringUsernameAuthenticationToken) authentication;
        assertEquals(expiry.toDate(), t.getTokenExpiration());

        verifyMock();
        verify(as);
    }