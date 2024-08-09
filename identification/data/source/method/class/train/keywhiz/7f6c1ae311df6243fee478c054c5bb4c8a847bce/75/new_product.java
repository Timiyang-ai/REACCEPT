@GET @Timed
  @Path("{name}/groups")
  public Iterable<String> secretGroupsListing(@Auth AutomationClient automationClient,
      @PathParam("name") String name) {
    // TODO: Use latest version instead of non-versioned
    Secret secret = secretController.getSecretByNameAndVersion(name, "")
        .orElseThrow(NotFoundException::new);
    return aclDAO.getGroupsFor(secret).stream()
        .map(Group::getName)
        .collect(toSet());
  }