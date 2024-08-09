    @Test
    public void authenticateWithPlainPassword() {
        // Given
        String loginId = "kjkmadness";
        String password = "pass";

        // When
        User user = UserApp.authenticateWithPlainPassword(loginId, password);

        // Then
        assertThat(user).isNotNull();
        assertThat(user.isAnonymous()).isFalse();
        assertThat(user.loginId).isEqualTo(loginId);
    }