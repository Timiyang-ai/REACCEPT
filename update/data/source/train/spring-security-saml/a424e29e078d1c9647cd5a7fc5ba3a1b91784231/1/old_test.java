@Test
    public void testProcessFilter() {
        entryPoint.setFilterSuffix("/saml/sso");
        expect(request.getRequestURI()).andReturn("/web/saml/sso");
        expect(request.getRequestURI()).andReturn("/saml/sso");
        expect(request.getRequestURI()).andReturn("/saml");
        expect(request.getRequestURI()).andReturn("/sso/");
        expect(request.getRequestURI()).andReturn("/saml/sso/");

        replayMock();
        assertTrue(entryPoint.processFilter(request));
        assertTrue(entryPoint.processFilter(request));
        assertFalse(entryPoint.processFilter(request));
        assertFalse(entryPoint.processFilter(request));
        assertTrue(entryPoint.processFilter(request));
        verifyMock();
    }