    @Test
    public void isProject() throws Exception {
        // Given
        String userName = "yobi";
        String projectName = "projectYobi";
        String newProjectName = "NanumFont";
        // When
        boolean result1 = Project.exists(userName, projectName);
        boolean result2 = Project.exists(userName, newProjectName);

        // Then
        assertThat(result1).isEqualTo(true);
        assertThat(result2).isEqualTo(false);
    }