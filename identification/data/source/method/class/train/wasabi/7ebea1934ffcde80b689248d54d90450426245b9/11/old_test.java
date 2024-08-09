    @Test
    public void checkSuperAdminPermissionsTest() {
        List<UserRole> spiedUserRole = new ArrayList<>();
        spiedUserRole.add(
                UserRole.builder()
                        .appName("test")
                        .role(CassandraAuthorizationRepository.SUPERADMIN)
                        .userId("test")
                        .build()
        );
        doReturn(spiedUserRole).when(spyRepository).getUserRolesWithWildcardAppName(
                com.intuit.wasabi.authenticationobjects.UserInfo.Username.valueOf("test"),
                Application.Name.valueOf("test")
        );

        UserPermissions result = spyRepository.checkSuperAdminPermissions(
                com.intuit.wasabi.authenticationobjects.UserInfo.Username.valueOf("test"),
                Application.Name.valueOf("test")
        );

        assertThat(result.getApplicationName().toString(), is("test"));
        assertThat(result.getPermissions(), is(Role.SUPERADMIN.getRolePermissions()));
    }