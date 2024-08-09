    @Test
    public void findByProjectId() throws Exception {
        // Given
        // When
        List<Milestone> firstProjectMilestones = Milestone.findByProjectId(1l);
        // Then
        assertThat(firstProjectMilestones.size()).isEqualTo(2);
        checkIfTheMilestoneIsBelongToTheProject(firstProjectMilestones, 1l, 2l);

        // Given
        // When
        List<Milestone> secondProjectMilestones = Milestone.findByProjectId(2l);
        // Then
        assertThat(secondProjectMilestones.size()).isEqualTo(2);
        checkIfTheMilestoneIsBelongToTheProject(secondProjectMilestones, 3l, 4l);
    }