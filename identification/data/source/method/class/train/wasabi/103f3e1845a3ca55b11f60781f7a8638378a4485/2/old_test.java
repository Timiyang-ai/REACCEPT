    @Test
    public void updateUserRoles() throws Exception {

        AuthorizationResource authorizationResource = new AuthorizationResource(authorization, new HttpHeader("jaba-???", "600"));

        UserRole userRole = UserRole.newInstance(TESTAPP, Role.ADMIN).withUserID(USER).build();
        UserRole userRole1 = UserRole.newInstance(TESTAPP2, Role.READWRITE).withUserID(TESTUSER).build();
        UserRoleList userRoleList = new UserRoleList();
        userRoleList.addRole(userRole);
        userRoleList.addRole(userRole1);

        List<HashMap> statuses = new ArrayList<>();

        HashMap<String, String> status = new HashMap<>();
        status.put("applicationName", userRole.getApplicationName().toString());
        status.put("userID", userRole.getUserID().toString());
        status.put("role", userRole.getRole().toString());
        status.put("roleAssignmentStatus", "SUCCESS");
        statuses.add(status);

        HashMap<String, String> status1 = new HashMap<>();
        status1.put("applicationName", userRole1.getApplicationName().toString());
        status1.put("userID", userRole1.getUserID().toString());
        status1.put("role", userRole1.getRole().toString());
        status1.put("roleAssignmentStatus", "FAILED");
        status1.put("reason", "Not Authorized");
        statuses.add(status1);

        HashMap<String, Object> statmap = new HashMap<>();
        statmap.put("assignmentStatuses", statuses);

        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(USER, TESTAPP2, Permission.ADMIN);
        when(authorization.getUserInfo(USER)).thenReturn(EventLog.SYSTEM_USER);
        when(authorization.setUserRole(userRole, EventLog.SYSTEM_USER)).thenReturn(status);

        Response response = authorizationResource.updateUserRoles(userRoleList, AUTHHEADER);
        assertThat(response.getEntity(), CoreMatchers.<Object>equalTo(statmap));
    }