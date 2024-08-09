@Test
    public void testGetSession()
            throws MessagingException
    {
        System.out.println("getSession");
        Session session;
        EmailService instance = getService(EmailServiceImpl.class);

        // Try to get a Session
        session = instance.getSession();
        assertNotNull(" getSession returned null", session);
    }