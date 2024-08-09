    @Test
    public void findMemberListByProject() throws Exception {
        // Given
        // When
        List<ProjectUser> projectUsers = ProjectUser.findMemberListByProject(1l);
        // Then
        assertThat(projectUsers.size()).isEqualTo(2);
        assertThat(projectUsers.get(0).user.id).isEqualTo(3l);
        assertThat(projectUsers.get(0).user.loginId).isEqualTo("laziel");
        assertThat(projectUsers.get(0).role.name).isEqualTo("member");
    }