    @Test
    public void findMilestones() throws Exception {
        // Given
        // When
        List<Milestone> p1InCmpleteMilestones = Milestone.findMilestones(1l,
            State.OPEN);
        // Then
        assertThat(p1InCmpleteMilestones.size()).isEqualTo(2);

        // Given
        // When
        List<Milestone> p2CompletedMilestones = Milestone.findMilestones(2l,
            State.CLOSED);
        // Then
        assertThat(p2CompletedMilestones.size()).isEqualTo(1);

        // Given
        // When
        List<Milestone> p2Milestones = Milestone.findMilestones(2l,
            State.ALL);
        // Then
        assertThat(p2Milestones.size()).isEqualTo(2);
    }