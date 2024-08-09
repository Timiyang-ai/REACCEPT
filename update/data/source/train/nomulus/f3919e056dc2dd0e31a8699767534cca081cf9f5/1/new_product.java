@CheckReturnValue
  public boolean checkRegistrarConsoleLogin(HttpServletRequest req, UserAuthInfo userAuthInfo) {
    checkState(userAuthInfo != null, "No logged in user found");
    User user = userAuthInfo.user();
    HttpSession session = req.getSession();
    String clientId = (String) session.getAttribute(CLIENT_ID_ATTRIBUTE);

    // Use the clientId if it exists
    if (clientId != null) {
      if (!hasAccessToRegistrar(clientId, user.getUserId())) {
        logger.infofmt("Registrar Console access revoked: %s", clientId);
        session.invalidate();
        return false;
      }
      logger.infofmt("Associating user %s with given registrar %s.", user.getUserId(), clientId);
      return true;
    }

    // The clientId was null, so let's try and find a registrar this user is associated with
    Optional<Registrar> registrar = findRegistrarForUser(user.getUserId());
    if (registrar.isPresent()) {
      verify(hasAccessToRegistrar(registrar.get(), user.getUserId()));
      logger.infofmt(
          "Associating user %s with found registrar %s.",
          user.getUserId(), registrar.get().getClientId());
      session.setAttribute(CLIENT_ID_ATTRIBUTE, registrar.get().getClientId());
      return true;
    }

    // We couldn't guess the registrar, but maybe the user is an admin and we can use the
    // registryAdminClientId
    if (userAuthInfo.isUserAdmin()) {
      if (Strings.isNullOrEmpty(registryAdminClientId)) {
        logger.infofmt(
            "Cannot associate admin user %s with configured client Id."
                + " ClientId is null or empty.",
            user.getUserId());
        return false;
      }
      if (!Registrar.loadByClientIdCached(registryAdminClientId).isPresent()) {
        logger.infofmt(
            "Cannot associate admin user %s with configured client Id %s."
                + " Registrar does not exist.",
            user.getUserId(), registryAdminClientId);
        return false;
      }
      logger.infofmt(
          "User %s is an admin with no associated registrar."
              + " Automatically associating the user with configured client Id %s.",
          user.getUserId(), registryAdminClientId);
      session.setAttribute(CLIENT_ID_ATTRIBUTE, registryAdminClientId);
      return true;
    }

    // We couldn't find any relevant clientId
    logger.infofmt("User not associated with any Registrar: %s", user.getUserId());
    return false;
  }