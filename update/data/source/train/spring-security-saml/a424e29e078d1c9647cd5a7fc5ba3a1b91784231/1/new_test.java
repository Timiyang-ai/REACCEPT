@Test
    public void testProcessFilter() {

        expect(request.getRequestURI()).andReturn("/web/saml/login");
        expect(request.getRequestURI()).andReturn("/saml/login");
        expect(request.getRequestURI()).andReturn("/saml");
        expect(request.getRequestURI()).andReturn("/login/");
        expect(request.getRequestURI()).andReturn("/saml/login/");

        replayMock();
        assertTrue(entryPoint.processFilter(request));
        assertTrue(entryPoint.processFilter(request));
        assertFalse(entryPoint.processFilter(request));
        assertFalse(entryPoint.processFilter(request));
        assertTrue(entryPoint.processFilter(request));
        verifyMock();
    }