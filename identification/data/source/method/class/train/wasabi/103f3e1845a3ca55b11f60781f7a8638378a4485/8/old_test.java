    @Test
    public void getUserPermissions() throws Exception {

        AuthorizationResource authorizationResource = new AuthorizationResource(authorization, new HttpHeader("jaba-???", "600"));

        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        UserPermissions userPermissions = UserPermissions.newInstance(TESTAPP,
                Role.SUPERADMIN.getRolePermissions()).build();
        UserPermissionsList userPermissionsList = new UserPermissionsList();
        userPermissionsList.addPermissions(userPermissions);

        when(authorization.getUserPermissionsList(USER)).thenReturn(userPermissionsList);
        Response response = authorizationResource.getUserPermissions(USER, AUTHHEADER);
        assertThat(response.getEntity(), CoreMatchers.<Object>equalTo(userPermissionsList));

        UserPermissions userPermissions1 = UserPermissions.newInstance(TESTAPP2,
                Role.READWRITE.getRolePermissions()).build();
        userPermissionsList.addPermissions(userPermissions1);
        when(authorization.getUserPermissionsList(TESTUSER)).thenReturn(userPermissionsList);
        response = authorizationResource.getUserPermissions(TESTUSER, AUTHHEADER);
        UserPermissionsList x = (UserPermissionsList) response.getEntity();
        assertThat(x.getPermissionsList(), equalTo(userPermissionsList.getPermissionsList()));
    }