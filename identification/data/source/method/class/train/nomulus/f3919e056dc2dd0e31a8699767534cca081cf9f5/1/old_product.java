@CheckReturnValue
  public boolean checkRegistrarConsoleLogin(HttpServletRequest req, User user) {
    checkState(user != null, "No logged in user found");
    HttpSession session = req.getSession();
    String clientId = (String) session.getAttribute(CLIENT_ID_ATTRIBUTE);
    if (clientId == null) {
      Optional<Registrar> registrar = guessRegistrar(user.getUserId());
      if (!registrar.isPresent()) {
        logger.infofmt("User not associated with any Registrar: %s (%s)",
            user.getUserId(), user.getEmail());
        return false;
      }
      verify(hasAccessToRegistrar(registrar.get(), user.getUserId()));
      session.setAttribute(CLIENT_ID_ATTRIBUTE, registrar.get().getClientId());
    } else {
      if (!hasAccessToRegistrar(clientId, user.getUserId())) {
        logger.infofmt("Registrar Console access revoked: %s for %s (%s)",
            clientId, user.getEmail(), user.getUserId());
        session.invalidate();
        return false;
      }
    }
    return true;
  }