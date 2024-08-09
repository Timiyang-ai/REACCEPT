@Test
  public void testGuessClientIdForUser_noAccess_isAdmin_adminClientIdEmpty() {
    sessionUtils.registryAdminClientId = "";
    expectGuessRegistrarFailure(
        UNAUTHORIZED_ADMIN, "User {user} isn't associated with any registrar");
    assertAboutLogs()
        .that(testLogHandler)
        .hasLogAtLevelWithMessage(
            Level.INFO,
            formatMessage(
                "Cannot associate admin user {user} with configured client Id."
                    + " ClientId is null or empty.",
                UNAUTHORIZED_ADMIN,
                null));
  }