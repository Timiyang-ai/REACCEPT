	@Test
    public void findUsersByProject() throws Exception {
        // Given
        // When
        List<User> users = User.findUsersByProject(2l);
        // Then
        assertThat(users.size()).isEqualTo(2);
    }