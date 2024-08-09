    @Test
    public void options() {
        // Given
        // When
        Map<String, String> milestoneOptions = Milestone.options(1l);
        // Then
        assertThat(milestoneOptions).hasSize(2);
    }