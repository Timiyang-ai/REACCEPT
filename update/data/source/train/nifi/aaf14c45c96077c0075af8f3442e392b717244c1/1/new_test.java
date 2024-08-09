@Test
    public void testSetAuthorities() throws Exception {
        UpdateUserAction setUserAuthorities = new UpdateUserAction(USER_ID_3, EnumSet.of(Authority.ROLE_ADMIN));
        NiFiUser user = setUserAuthorities.execute(daoFactory, authorityProvider);

        // verify user
        Assert.assertEquals(USER_ID_3, user.getId());
        Assert.assertEquals(1, user.getAuthorities().size());
        Assert.assertTrue(user.getAuthorities().contains(Authority.ROLE_ADMIN));

        // verify interaction with dao
        Mockito.verify(userDao, Mockito.times(1)).updateUser(user);
        Mockito.verify(authorityDao, Mockito.times(1)).createAuthorities(EnumSet.of(Authority.ROLE_ADMIN), USER_ID_3);

        Set<Authority> authoritiesAddedToProvider = EnumSet.of(Authority.ROLE_ADMIN);

        // verify interaction with provider
        Mockito.verify(authorityProvider, Mockito.times(1)).setAuthorities(USER_IDENTITY_3, authoritiesAddedToProvider);
    }