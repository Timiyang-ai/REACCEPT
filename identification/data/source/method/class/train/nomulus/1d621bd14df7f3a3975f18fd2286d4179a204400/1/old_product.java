public Registrar getRegistrarForUser(String clientId, AuthResult authResult) {
    return getAndAuthorize(Registrar::loadByClientId, clientId, authResult);
  }