    @Test
    public void getUserAppPermissions() throws Exception {

        AuthorizationResource authorizationResource = new AuthorizationResource(authorization, new HttpHeader("jaba-???", "600"));

        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        UserPermissions userPermissions = UserPermissions.newInstance(TESTAPP,
                Role.SUPERADMIN.getRolePermissions()).build();
        when(authorization.getUserPermissions(USER, TESTAPP)).thenReturn(userPermissions);
        UserPermissions userPermissions1 = UserPermissions.newInstance(TESTAPP,
                Role.READONLY.getRolePermissions()).build();
        when(authorization.getUserPermissions(TESTUSER, TESTAPP2)).thenReturn(userPermissions1);

        Response response = authorizationResource.getUserAppPermissions(USER, TESTAPP, AUTHHEADER);
        assertThat(response.getEntity(), CoreMatchers.<Object>equalTo(userPermissions));

        response = authorizationResource.getUserAppPermissions(TESTUSER, TESTAPP2, AUTHHEADER);
        assertThat(response.getEntity(), CoreMatchers.<Object>equalTo(userPermissions1));
    }