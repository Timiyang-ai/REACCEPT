public String guessClientIdForUser(AuthResult authResult) {

    UserAuthInfo userAuthInfo =
        authResult.userAuthInfo().orElseThrow(() -> new ForbiddenException("No logged in"));
    boolean isAdmin = userAuthInfo.isUserAdmin();
    User user = userAuthInfo.user();
    String userIdForLogging = authResult.userIdForLogging();

    RegistrarContact contact =
        ofy()
            .load()
            .type(RegistrarContact.class)
            .filter("gaeUserId", user.getUserId())
            .first()
            .now();
    if (contact != null) {
      String registrarClientId = contact.getParent().getName();
      logger.atInfo().log(
          "Associating user %s with found registrar %s.", userIdForLogging, registrarClientId);
      return registrarClientId;
    }

    // We couldn't find the registrar, but maybe the user is an admin and we can use the
    // registryAdminClientId
    if (isAdmin) {
      if (!Strings.isNullOrEmpty(registryAdminClientId)) {
        logger.atInfo().log(
            "User %s is an admin with no associated registrar."
                + " Automatically associating the user with configured client Id %s.",
            userIdForLogging, registryAdminClientId);
        return registryAdminClientId;
      }
      logger.atInfo().log(
          "Cannot associate admin user %s with configured client Id."
              + " ClientId is null or empty.",
          userIdForLogging);
    }

    // We couldn't find any relevant clientId
    throw new ForbiddenException(
        String.format("User %s isn't associated with any registrar", userIdForLogging));
  }