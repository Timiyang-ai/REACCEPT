@Test
    public void testAuthenticate() throws Exception {
        SAMLMessageContext context = new SAMLMessageContext();

        SAMLAuthenticationToken token = new SAMLAuthenticationToken(context, messageStorage);
        SAMLMessageStorage store = token.getMessageStore();
        SAMLCredential result = new SAMLCredential(nameID, assertion, "IDP", "testSP");

        expect(consumer.processAuthenticationResponse(context, store)).andReturn(result);
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