@Test
    public void testStartRequest() {
        String requestId = sessionRequestService.startRequest();
        assertNotNull(requestId);

        sessionRequestService.endRequest(null);
    }