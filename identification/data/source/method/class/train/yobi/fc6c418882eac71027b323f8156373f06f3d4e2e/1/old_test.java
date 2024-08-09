    @Test
    public void projectNameChangeable() throws Exception {
        // Given
        String userName = "yobi";
        Long projectId = 1l;
        String newProjectName1 = "HelloSocialApp";
        String newProjectName2 = "NanumFont";
        // When
        boolean result1 = Project.projectNameChangeable(projectId, userName, newProjectName1);
        boolean result2 = Project.projectNameChangeable(projectId, userName, newProjectName2);
        // Then
        assertThat(result1).isEqualTo(false);
        assertThat(result2).isEqualTo(true);
    }