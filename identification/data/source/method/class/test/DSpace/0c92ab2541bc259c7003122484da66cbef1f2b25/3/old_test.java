@Test
    public void testGetCurrentSessionId() {
        String current = sessionRequestService.getCurrentSessionId();
        assertNull(current);

        Session session = sessionRequestService.startSession("aaronz");
        assertNotNull(session);
        assertEquals("aaronz", session.getId());

        current = sessionRequestService.getCurrentSessionId();
        assertNotNull(current);
        assertEquals("aaronz", current);
        assertEquals(current, session.getId());
    }