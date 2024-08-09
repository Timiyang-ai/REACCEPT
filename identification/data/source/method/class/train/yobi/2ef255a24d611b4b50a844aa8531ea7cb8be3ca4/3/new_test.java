    @Test
    public void isMember() throws Exception {
        // Given
        // When
        // Then
        assertThat(ProjectUser.isMember(2l, 2l)).isEqualTo(true);
        assertThat(ProjectUser.isMember(2l, 3l)).isEqualTo(false);
    }