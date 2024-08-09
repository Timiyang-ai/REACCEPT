@Test
    public void testGetSession()
            throws MessagingException
    {
        System.out.println("getSession");
        Session session;
        EmailService instance = getService(EmailService.class);

        // Try to get a Session
        session = instance.getSession();
        assertNotNull(" getSession returned null", session);
    }