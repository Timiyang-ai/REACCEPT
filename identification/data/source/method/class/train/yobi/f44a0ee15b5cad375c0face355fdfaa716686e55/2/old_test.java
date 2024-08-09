    @Test
    public void options() throws Exception {
        // Given
        // When
        // Then
        assertThat(ProjectUser.options(1l).containsValue("laziel")).isEqualTo(true);
    }