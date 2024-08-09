    @Test
    public void isManager() throws Exception {
        // Given
        ProjectUser.assignRole(2l, 3l, 1l);
        flush();

        // When
        Long userIdCase1 = 2l;
        Long userIdCase2 = 5l;

        // Then
        assertThat(ProjectUser.checkOneMangerPerOneProject(userIdCase1, 3l)).isEqualTo(true);
        assertThat(ProjectUser.checkOneMangerPerOneProject(userIdCase2, 3l)).isEqualTo(false);
        // To keep data clean after this test.
        ProjectUser.delete(2l, 3l);
    }