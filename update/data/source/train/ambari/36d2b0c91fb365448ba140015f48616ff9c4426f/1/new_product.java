@Override
  public RequestStatus deleteResources(Request request, Predicate predicate)
      throws SystemException, UnsupportedPropertyException, NoSuchResourceException, NoSuchParentResourceException {
    Authentication authentication = AuthorizationHelper.getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
      throw new AuthorizationException("Authentication data is not available, authorization to perform the requested operation is not granted");
    } else if (!isAuthorizedToDeleteResources(authentication, predicate)) {
      throw new AuthorizationException("The authenticated user does not have the appropriate authorizations to delete the requested resource(s)");
    }

    return deleteResourcesAuthorized(request, predicate);
  }