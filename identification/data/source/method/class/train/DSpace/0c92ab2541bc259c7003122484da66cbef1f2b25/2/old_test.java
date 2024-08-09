@Test
    public void testEndRequest() {
        String requestId = sessionRequestService.startRequest();
        assertNotNull(requestId);

        sessionRequestService.endRequest(null);
        String cacheRID = (String) getRequestCache().get(CachingService.REQUEST_ID_KEY);
        assertNull(cacheRID);
    }