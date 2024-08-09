@Test
    public void testGetCurrentSessionId() {
        String current = sessionRequestService.getCurrentSessionId();
        assertNull(current);
    }