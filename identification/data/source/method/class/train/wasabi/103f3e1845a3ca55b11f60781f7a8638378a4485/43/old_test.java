    @Test
    public void getUserExists() throws Exception {
        UserInfo userInfo = UserInfo.newInstance(USER).build();

        UserRole userRole = UserRole.newInstance(TESTAPP, Role.ADMIN).withUserID(USER).build();
        UserRole userRole1 = UserRole.newInstance(TESTAPP2, Role.READWRITE).withUserID(USER).build();
        UserRoleList userRoleList = new UserRoleList();
        userRoleList.addRole(userRole);
        userRoleList.addRole(userRole1);

        when(authentication.getUserExists("username@a.b")).thenReturn(userInfo);
        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(authorization.getUserRoleList(USER)).thenReturn(userRoleList);

        Response response = authenticationResource.getUserExists("username@a.b", AUTHHEADER);
        assert (userInfo.equals(response.getEntity()));
        Mockito.verify(authorization).getUser(AUTHHEADER);
    }