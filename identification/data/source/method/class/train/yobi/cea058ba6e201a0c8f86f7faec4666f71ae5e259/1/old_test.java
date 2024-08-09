    @Test
    public void isAllowed_siteAdmin() {
        // Given
        User admin = User.findByLoginId("admin");
        Project projectYobi = Project.findByOwnerAndProjectName("yobi", "projectYobi");

        // When
        boolean canUpdate = AccessControl.isAllowed(admin, projectYobi.asResource(), Operation.UPDATE);
        boolean canRead = AccessControl.isAllowed(admin, projectYobi.asResource(), Operation.READ);
        boolean canDelete = AccessControl.isAllowed(admin, projectYobi.asResource(), Operation.DELETE);

        // Then
        assertThat(canRead).isEqualTo(true);
        assertThat(canUpdate).isEqualTo(true);
        assertThat(canDelete).isEqualTo(true);
    }