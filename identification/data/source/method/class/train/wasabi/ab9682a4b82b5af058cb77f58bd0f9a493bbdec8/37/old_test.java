    @Test
    public void getApplicationUsersByRole() throws Exception {

        AuthorizationResource authorizationResource = new AuthorizationResource(authorization, new HttpHeader("jaba-???", "600"));

        UserRole userRole = UserRole.newInstance(TESTAPP, Role.ADMIN).withUserID(USER).build();
        UserRole userRole1 = UserRole.newInstance(TESTAPP2, Role.READWRITE).withUserID(USER).build();
        UserRoleList userRoleList = new UserRoleList();
        userRoleList.addRole(userRole);
        userRoleList.addRole(userRole1);

        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(authorization.getApplicationUsers(TESTAPP)).thenReturn(userRoleList);

        Response response = authorizationResource.getApplicationUsersByRole(TESTAPP, AUTHHEADER);
        assertThat(response.getEntity(), CoreMatchers.<Object>equalTo(userRoleList));
    }