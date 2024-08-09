    @Test
    public void delete() throws Exception {
        // Given
        Project project = getNewProject();
        Project.create(project);
        long projectId = project.id;
        // When
        Project.find.byId(projectId).delete();

        // Then
        assertThat(Project.find.byId(projectId)).isNull();
        assertThat(ProjectUser.findMemberListByProject(projectId)).isEmpty();
        assertThat(Issue.finder.where().eq("project.id", projectId).findList()).isEmpty();
        assertThat(Milestone.findByProjectId(projectId)).isEmpty();
    }