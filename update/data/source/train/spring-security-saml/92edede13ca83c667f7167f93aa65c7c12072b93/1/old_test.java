@Test
    public void testAuthenticate() throws Exception {
        BasicSAMLMessageContext context = new BasicSAMLMessageContext();

        SAMLAuthenticationToken token = new SAMLAuthenticationToken(context, messageStorage);
        SAMLMessageStorage store = token.getMessageStore();
        SAMLCredential result = new SAMLCredential(nameID, assertion, "IDP");

        expect(consumer.processResponse(context, store)).andReturn(result);
        expect(nameID.getValue()).andReturn("Name");

        replayMock();
        Authentication authentication = provider.authenticate(token);
        assertEquals("Name", authentication.getName());
        verifyMock();
    }