public ImmutableSetMultimap<AccessType, String> getAllClientIdWithAccess() {
    verifyLoggedIn();
    return accessMap;
  }