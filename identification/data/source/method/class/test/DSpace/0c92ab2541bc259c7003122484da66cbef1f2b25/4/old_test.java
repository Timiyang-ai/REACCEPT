@Test
    public void testGetCurrentSession() {
        Session current = sessionRequestService.getCurrentSession();
        assertNull(current);

        Session session = sessionRequestService.startSession("aaronz");
        assertNotNull(session);
        assertEquals("aaronz", session.getId());

        current = sessionRequestService.getCurrentSession();
        assertNotNull(current);
        assertEquals("aaronz", current.getId());
        assertEquals(current, session);
    }