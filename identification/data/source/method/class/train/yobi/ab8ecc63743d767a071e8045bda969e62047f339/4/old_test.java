    @Test
    public void authenticateWithHashedPassword() {
        // Given
        String loginId = "kjkmadness";
        String password = "ckJUVVaOHhRDNqwbeF+j4RNqXzodXO95+aQRIbJnDK4=";

        // When
        User user = UserApp.authenticateWithHashedPassword(loginId, password);

        // Then
        assertThat(user).isNotNull();
        assertThat(user.isAnonymous()).isFalse();
        assertThat(user.loginId).isEqualTo(loginId);
    }