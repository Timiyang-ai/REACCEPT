    @Test
    public void isEmailExist() throws Exception {
        // Given
        String expectedUser = "doortts@gmail.com";

    	// When // Then
        assertThat(User.isEmailExist(expectedUser)).isTrue();
    }