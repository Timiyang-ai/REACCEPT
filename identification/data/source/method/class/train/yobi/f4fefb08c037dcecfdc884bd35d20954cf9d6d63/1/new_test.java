    @Test
    public void isAllowedToSettings() {
        // GIVEN
        String loginId = "yobi";
        Project project = Project.findByOwnerAndProjectName(loginId, "projectYobi");
        // WHEN // THEN
        assertThat(ProjectUser.isAllowedToSettings(loginId, project)).isTrue();
        // WHEN // THEN
        assertThat(ProjectUser.isAllowedToSettings("admin", project)).isTrue();
        // WHEN // THEN
        assertThat(ProjectUser.isAllowedToSettings(null, project)).isFalse();
        // WHEN // THEN
        assertThat(ProjectUser.isAllowedToSettings("keesun", project)).isFalse();

    }