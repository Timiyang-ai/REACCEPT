@Test
  public void testCheckRegistrarConsoleLogin_noSession_noAccess_isAdmin() throws Exception {
    assertThat(sessionUtils.checkRegistrarConsoleLogin(req, UNAUTHORIZED_ADMIN)).isTrue();
    verify(session).setAttribute(eq("clientId"), eq(ADMIN_CLIENT_ID));
    assertAboutLogs()
        .that(testLogHandler)
        .hasLogAtLevelWithMessage(
            Level.INFO,
            String.format(
                "User %s is an admin with no associated registrar."
                    + " Automatically associating the user with configured client Id %s.",
                UNAUTHORIZED_ADMIN.user().getUserId(), ADMIN_CLIENT_ID));
  }