    @Test
    public void getApplicationUsersTest() {
        Application.Name applicaitonName = Application.Name.valueOf("TestApp");
        List<AppRole> appRoleList = new ArrayList<>();
        appRoleList.add(AppRole.builder()
                .appName(applicaitonName.toString())
                .userId("user1")
                .role(Role.ADMIN.name())
                .build()
        );
        com.intuit.wasabi.authenticationobjects.UserInfo userInfo =
                new com.intuit.wasabi.authenticationobjects.UserInfo.Builder(
                        com.intuit.wasabi.authenticationobjects.UserInfo.Username.valueOf("user1")
                )
                        .withFirstName("firstname")
                        .withLastName("lastname")
                        .withUserId("user1")
                        .withEmail("test@test.com")
                        .build();
        doReturn(userInfo).when(spyRepository)
                .getUserInfo(eq(com.intuit.wasabi.authenticationobjects.UserInfo.Username.valueOf("user1")));
        doReturn(appRoleList).when(spyRepository).getAppRoleList(eq(applicaitonName));
        UserRoleList result = spyRepository.getApplicationUsers(applicaitonName);
        assertThat(result.getRoleList().size(), is(1));
        com.intuit.wasabi.authorizationobjects.UserRole userRole = result.getRoleList().get(0);
        assertThat(userRole.getRole(), is(Role.ADMIN));
        assertThat(userRole.getUserID().getUsername(), is(userInfo.getUserId()));
        assertThat(userRole.getApplicationName(), is(applicaitonName));
        assertThat(userRole.getUserEmail(), is(userInfo.getEmail()));
        assertThat(userRole.getFirstName(), is(userInfo.getFirstName()));
        assertThat(userRole.getLastName(), is(userInfo.getLastName()));
    }