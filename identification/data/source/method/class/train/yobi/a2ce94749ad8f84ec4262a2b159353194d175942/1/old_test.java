    @Test
    public void importForm() {
        // Given
        User user = User.find.byId(2L);

        // When
        Result result = callAction(ref.ImportApp.importForm(),
                fakeRequest().withSession(UserApp.SESSION_USERID, String.valueOf(user.id)));

        // Then
        assertThat(status(result)).isEqualTo(OK);
    }