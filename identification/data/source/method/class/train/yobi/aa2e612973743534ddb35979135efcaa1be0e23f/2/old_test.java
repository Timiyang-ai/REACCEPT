    @Test
    public void findByIds() throws Exception {
        // Given
        // When
        Role role = ProjectUser.findByIds(2l, 1l).role;
        // Then
        assertThat(role.id).isEqualTo(1l);
    }