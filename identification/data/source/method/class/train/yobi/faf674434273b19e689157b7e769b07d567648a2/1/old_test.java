    @Test
    public void roleOf() {
        // GIVEN
        String loginId = "yobi";
        Project project = Project.findByOwnerAndProjectName(loginId, "projectYobi");
        // WHEN
        String roleName = ProjectUser.roleOf(loginId, project);
        // THEN
        assertThat(roleName).isEqualTo("manager");

        // WHEN
        roleName = ProjectUser.roleOf("admin", project);
        // THEN
        assertThat(roleName).isEqualTo("sitemanager");

        // WHEN
        roleName = ProjectUser.roleOf((String) null, project);
        // THEN
        assertThat(roleName).isEqualTo("anonymous");

        // WHEN
        roleName = ProjectUser.roleOf("keesun", project);
        // THEN
        assertThat(roleName).isEqualTo("anonymous");

        // WHEN
        roleName = ProjectUser.roleOf("laziel", project);
        // THEN
        assertThat(roleName).isEqualTo("member");
    }