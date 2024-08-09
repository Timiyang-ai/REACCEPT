    @Test
    public void findClosedMilestones() throws Exception {
        // Given
        // When
        List<Milestone> p1Milestones = Milestone.findClosedMilestones(1l);
        // Then
        assertThat(p1Milestones.size()).isEqualTo(0);

        // Given
        // When
        List<Milestone> p2Milestones = Milestone.findClosedMilestones(2l);
        // Then
        assertThat(p2Milestones.size()).isEqualTo(1);
    }