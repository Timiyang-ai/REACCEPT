@Test
    public void testEndRequest() {
        String requestId = sessionRequestService.startRequest();
        assertNotNull(requestId);

        sessionRequestService.endRequest(null);
        assertNull( getRequestCache() );
    }