@Test
    public void testGetCurrentSession() {
        Session current = sessionRequestService.getCurrentSession();
        assertNull(current);
    }