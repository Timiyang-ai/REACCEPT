    @Test
    public void authenticate() throws Exception {
        LdapUser ldapUser = new LdapUser(username, "a@b", "b", "banner", principal, "", false, null);
        when(ldapManager.getUser(username, domainId)).thenReturn(ldapUser);
        when(ldapManager.canAuthenticate(principal, hardcoded, domainId)).thenReturn(true);
        Pair<Boolean, UserAuthenticator.ActionOnFailedAuthentication> rc = ldapAuthenticator.authenticate(username, hardcoded, domainId, user);
        assertTrue("authentication failed when it should have succeeded", rc.first());
        assertNull(rc.second());
    }