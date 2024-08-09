    @Test
    public void assignRole() throws Exception {
        // Given
        // When
        ProjectUser.assignRole(2l, 1l, 2l);
        ProjectUser.assignRole(2l, 3l, 2l);
        flush();
        // Then
        assertThat(ProjectUser.findByIds(2l, 1l).role.id).isEqualTo(2l);
        assertThat(ProjectUser.findByIds(2l, 3l).role.id).isEqualTo(2l);
        // To keep data clean after this test.
        ProjectUser.assignRole(2l, 1l, 1l);
        ProjectUser.delete(2l, 3l);
    }