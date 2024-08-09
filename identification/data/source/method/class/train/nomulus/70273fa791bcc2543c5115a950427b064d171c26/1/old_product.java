public String guessClientIdForUser(AuthResult authResult) {

    UserAuthInfo userAuthInfo =
        authResult.userAuthInfo().orElseThrow(() -> new ForbiddenException("No logged in"));
    boolean isAdmin = userAuthInfo.isUserAdmin();
    User user = userAuthInfo.user();
    String gaeUserId = user.getUserId();

    RegistrarContact contact =
        ofy().load().type(RegistrarContact.class).filter("gaeUserId", gaeUserId).first().now();
    if (contact != null) {
      String registrarClientId = contact.getParent().getName();
      logger.atInfo().log(
          "Associating user %s with found registrar %s.", gaeUserId, registrarClientId);
      return registrarClientId;
    }

    // We couldn't find the registrar, but maybe the user is an admin and we can use the
    // registryAdminClientId
    if (isAdmin) {
      if (!Strings.isNullOrEmpty(registryAdminClientId)) {
        logger.atInfo().log(
            "User %s is an admin with no associated registrar."
                + " Automatically associating the user with configured client Id %s.",
            gaeUserId, registryAdminClientId);
        return registryAdminClientId;
      }
      logger.atInfo().log(
          "Cannot associate admin user %s with configured client Id."
              + " ClientId is null or empty.",
          gaeUserId);
    }

    // We couldn't find any relevant clientId
    throw new ForbiddenException(
        String.format("User %s isn't associated with any registrar", gaeUserId));
  }