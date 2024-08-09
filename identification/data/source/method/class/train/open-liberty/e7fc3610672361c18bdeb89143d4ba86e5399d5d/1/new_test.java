@Test
    public void initialize_minimumConfiguration() throws Exception {
        ContextManager cm = new ContextManager();
        cm.setPrimaryServer("localhost", primaryLdapServer.getLdapServer().getPort());
        cm.initialize();

        /*
         * Get a context.
         */
        TimedDirContext ctx = cm.getDirContext();
        assertNotNull("Expected to get a TimedDirContext instance.", ctx);

        /*
         * Search will fail as the anonymous bind has no permissions on our
         * embedded LDAP server.
         */
        try {
            ctx.search(BASE_ENTRY, "uid=user1", null);
            fail("Expected NoPermissionException");
        } catch (NoPermissionException e) {
            // Passed.
        }
    }