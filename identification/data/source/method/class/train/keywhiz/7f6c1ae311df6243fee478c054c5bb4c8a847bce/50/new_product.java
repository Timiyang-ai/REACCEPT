@Timed @ExceptionMetered
  @GET
  @Path("{name}/groups")
  @Produces(APPLICATION_JSON)
  public Iterable<String> secretGroupsListing(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    // TODO: Use latest version instead of non-versioned
    Secret secret = secretControllerReadOnly.getSecretByName(name)
        .orElseThrow(NotFoundException::new);
    return aclDAO.getGroupsFor(secret).stream()
        .map(Group::getName)
        .collect(toSet());
  }