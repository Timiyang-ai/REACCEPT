    @Test
    public void setUserRoleTest() {
        Session mockSession = mock(Session.class);
        when(mappingMapager.getSession()).thenReturn(mockSession);
        com.intuit.wasabi.authorizationobjects.UserRole userRole = com.intuit.wasabi.authorizationobjects.UserRole
                .newInstance(Application.Name.valueOf("TestApp"), Role.ADMIN)
                .withUserID(com.intuit.wasabi.authenticationobjects.UserInfo.Username.valueOf("testuser"))
                .withLastName("lastname")
                .withFirstName("firstname")
                .build();
        repository.setUserRole(userRole);
        verify(userRoleAccessor, times(1)).insertUserRoleStatement(eq("testuser"), eq("TestApp"), eq(Role.ADMIN.name()));
        verify(appRoleAccessor, times(1)).insertAppRoleStatement(eq("TestApp"), eq("testuser"), eq(Role.ADMIN.name()));
        verify(mockSession, times(1)).execute(any(Statement.class));
    }