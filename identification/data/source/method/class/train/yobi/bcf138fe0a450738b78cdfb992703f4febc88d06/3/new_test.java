    @Test
    public void getAllProjectRoles() throws Exception {
        // Given
        // When
        List<Role> roles = Role.findProjectRoles();
        // Then
        assertThat(roles.contains(Role.findByName("siteManager"))).isEqualTo(false);
        assertThat(roles.contains(Role.findByName("manager"))).isEqualTo(true);
        assertThat(roles.contains(Role.findByName("member"))).isEqualTo(true);
    }