    @Test
    public void findOpenMilestones() throws Exception {
        // Given
        // When
        List<Milestone> p1Milestones = Milestone.findOpenMilestones(1l);

        // Then
        assertThat(p1Milestones.size()).isEqualTo(2);

        // Given
        // When
        List<Milestone> p2Milestones = Milestone.findOpenMilestones(2l);

        // Then
        assertThat(p2Milestones.size()).isEqualTo(1);
    }