    @Test
    public void getUserRole() throws Exception {

        AuthorizationResource authorizationResource = new AuthorizationResource(authorization, new HttpHeader("jaba-???", "600"));

        UserRole userRole = UserRole.newInstance(TESTAPP, Role.ADMIN).withUserID(USER).build();
        UserRole userRole1 = UserRole.newInstance(TESTAPP2, Role.READWRITE).withUserID(USER).build();
        UserRoleList userRoleList = new UserRoleList();
        userRoleList.addRole(userRole);
        userRoleList.addRole(userRole1);

        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(authorization.getUserRoleList(USER)).thenReturn(userRoleList);
        Response response = authorizationResource.getUserRole(USER, AUTHHEADER);
        assert (userRoleList.equals(response.getEntity()));

        userRole = UserRole.newInstance(TESTAPP, Role.ADMIN).withUserID(TESTUSER).build();
        userRole1 = UserRole.newInstance(TESTAPP2, Role.READWRITE).withUserID(TESTUSER).build();
        userRoleList = new UserRoleList();
        userRoleList.addRole(userRole);
        userRoleList.addRole(userRole1);

        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(USER, TESTAPP2, Permission.ADMIN);
        when(authorization.getUserRoleList(TESTUSER)).thenReturn(userRoleList);
        response = authorizationResource.getUserRole(TESTUSER, AUTHHEADER);
        UserRoleList userRoleList1 = new UserRoleList();
        userRoleList1.addRole(userRoleList.getRoleList().get(0));
        UserRoleList x = (UserRoleList) response.getEntity();
        assert (x.equals(userRoleList1));
    }