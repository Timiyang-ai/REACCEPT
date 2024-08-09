@Test
    public void testStartRequest() {
        String requestId = sessionRequestService.startRequest();
        assertNotNull(requestId);
        String cacheRID = (String) getRequestCache().get(CachingService.REQUEST_ID_KEY);
        assertNotNull(cacheRID);
        assertEquals(cacheRID, requestId);

        sessionRequestService.endRequest(null);
    }