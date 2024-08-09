public Registrar getRegistrarForUser(
      String clientId, AccessType accessType, AuthResult authResult) {
    return getAndAuthorize(Registrar::loadByClientId, clientId, accessType, authResult);
  }